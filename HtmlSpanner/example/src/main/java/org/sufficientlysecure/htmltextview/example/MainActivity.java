/*
 * Copyright (C) 2013-2016 Dominik Schürmann <dominik@dominikschuermann.de>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sufficientlysecure.htmltextview.example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.sysdata.htmlspanner.HtmlSpanner;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends Activity {

    private static final String LOG_TAG="readHtml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String html=loadStringFromAssetFile(this,"example.html");
        TextView tv = (TextView) findViewById(R.id.twxt);
        int col=tv.getSolidColor();
        int fontHeight = tv.getPaint().getFontMetricsInt(null);
        Log.i("font height",""+fontHeight);
        HtmlSpanner htmlSpanner=new HtmlSpanner(tv.getTextColors().getDefaultColor(),tv.getTextSize());
        htmlSpanner.setBackgroundColor(col);
        tv.setText(htmlSpanner.fromHtml(html));
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * This method load a string from an asset file
     *
     * @param filename
     * @return
     */
    public static String loadStringFromAssetFile(Context context,String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e(LOG_TAG, ex.getMessage());
            return null;
        }
        return json;
    }
}
