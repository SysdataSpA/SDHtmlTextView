/*
 * Copyright (C) 2011 Alex Kuiper <http://www.nightwhistler.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.nightwhistler.htmlspanner.handlers;

import java.io.IOException;
import java.net.URL;

import net.nightwhistler.htmlspanner.SpanStack;
import net.nightwhistler.htmlspanner.TagNodeHandler;

import org.htmlcleaner.TagNode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Handles image tags.
 * 
 * The default implementation tries to load images through a URL.openStream(),
 * override loadBitmap() to implement your own loading.
 * 
 * @author Alex Kuiper
 * 
 */
public class ImageHandler extends TagNodeHandler {

    private static final String ERRORTAG = "Image error";
    private static final long MAXIMUM_TIME=450;

    @Override
	public void handleTagNode(TagNode node, final SpannableStringBuilder builder,
							  final int start, final int end, final SpanStack stack) {
		String src = node.getAttributeByName("src");

		builder.append("\uFFFC");

        synchronized (this) {
            // we load the image on a different thread
            Observable.just(src).observeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    // do nothing
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(ERRORTAG, "" + e);
                }

                @Override
                public void onNext(String s) {
                    Bitmap bitmap = loadBitmap(s);
                    if (bitmap != null) {
                        Drawable drawable = new BitmapDrawable(bitmap);
                        drawable.setBounds(0, 0, bitmap.getWidth() - 1,
                                bitmap.getHeight() - 1);
                        stack.pushSpan(new ImageSpan(drawable), start, builder.length());
                    }
                }
            });
            // we wait until the image is loaded,
            // if the image is not loaded until the maximum time we didn't show it
            try {
                this.wait(MAXIMUM_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

	/**
	 * Loads a Bitmap from the given url.
	 * 
	 * @param url
	 * @return a Bitmap, or null if it could not be loaded.
	 */
	protected Bitmap loadBitmap(String url) {
		try {
			return BitmapFactory.decodeStream(new URL(url).openStream());
		} catch (IOException io) {
			return null;
		}
	}
}
