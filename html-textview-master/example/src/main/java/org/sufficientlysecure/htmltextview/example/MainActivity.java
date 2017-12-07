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
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import net.nightwhistler.htmlspanner.HtmlSpanner;

import org.sufficientlysecure.htmltextview.ClickableTableSpan;
import org.sufficientlysecure.htmltextview.DrawTableLinkSpan;
import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import us.feras.mdv.MarkdownView;

import static org.sufficientlysecure.htmltextview.example.WebViewActivity.EXTRA_TABLE_HTML;

public class MainActivity extends Activity {

    // The html table(s) are individually passed through to the ClickableTableSpan implementation
    // presumably for a WebView activity.
    class ClickableTableSpanImpl extends ClickableTableSpan {
        @Override
        public ClickableTableSpan newInstance() {
            return new ClickableTableSpanImpl();
        }

        @Override
        public void onClick(View widget) {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            intent.putExtra(EXTRA_TABLE_HTML, getTableHtml());
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        HtmlTextView textView = (HtmlTextView) findViewById(R.id.html_text);

//        textView.setClickableTableSpan(new ClickableTableSpanImpl());
//        DrawTableLinkSpan drawTableLinkSpan = new DrawTableLinkSpan();
//        drawTableLinkSpan.setTableLinkText("[tap for table]");
//        textView.setDrawTableLinkSpan(drawTableLinkSpan);
//
//        textView.setHtml(R.raw.example, new HtmlResImageGetter(textView));

//        MarkdownView markdownView = (MarkdownView) findViewById(R.id.markdownView);
//        markdownView.loadMarkdownFile("file:///android_asset/testHtmlTags.html");

//        WebView markdownView = (WebView) findViewById(R.id.markdownView);
//        markdownView.loadUrl("file:///android_asset/testHtmlStyle.html");

        String html="<div style=\"color:red;font-size: 1.5em\"><p>" +
                "    <span style=\"font-style: italic;\">Lorem ipsum dolor sit amet, </span>consectetur adipiscing elit. <br>Sed porttitor mauris eu lorem sodales luctus. Cras ultrices purus posuere risus faucibus, at fringilla erat pulvinar. Vestibulum eu augue posuere quam ornare aliquet vitae sit amet nisi. Cras dignissim est vel neque finibus, mollis elementum lectus dapibus. Nulla facilisi. Donec ipsum nisi, feugiat ac pulvinar a, auctor id lorem. Proin in aliquam mauris, eget vulputate lorem. Nam congue leo quis commodo fringilla. Curabitur posuere in metus eu consequat. Duis facilisis ultrices augue in mattis. In porta pretium arcu, a vulputate erat posuere et. Vivamus nec ipsum id diam vehicula pharetra eu vitae metus. Nunc placerat risus quis scelerisque euismod. Curabitur molestie consequat mauris non maximus. Nullam et neque viverra orci euismod euismod. Etiam ultricies ante elit, id condimentum arcu dignissim nec.\n" +
                "    </p><p style=\"line-height:50px;\">" +
                "    Pellentesque consectetur risus metus, sit amet tempor dolor rhoncus quis. Suspendisse vitae arcu placerat, feugiat diam sed, rutrum justo. Praesent mi nunc, dictum lacinia nibh vitae, lacinia eleifend nibh. <span style=\"background-color:blue; color:white;\"> Donec feugiat est vel libero tristique,</span> sed aliquam ipsum mattis. Ut pretium, mi id scelerisque blandit, justo lectus ornare nunc, ac posuere arcu mauris nec sapien. Curabitur pharetra, sapien ac accumsan scelerisque, eros augue tincidunt arcu, at sagittis eros nibh at lorem. Vivamus vitae pulvinar mi, ac vestibulum nulla. In eget arcu enim. Fusce egestas ullamcorper risus, non pellentesque mauris feugiat at." +
                "    </p><p style=\"background-color: purple; color:white;\">" +
                "    Pellentesque consectetur risus metus, <a href=\"http://www.google.com\">Google</a> tempor dolor rhoncus quis. Suspendisse vitae arcu placerat, feugiat diam sed, rutrum justo. \nPraesent mi nunc, dictum lacinia nibh vitae, lacinia eleifend nibh. <span style=\"background-color: blue;color:white;\">Donec feugiat est vel libero tristique,</span> sed aliquam ipsum mattis. Ut pretium, mi id scelerisque blandit, justo lectus ornare nunc, ac posuere arcu mauris nec sapien. Curabitur pharetra, sapien ac accumsan scelerisque, eros augue tincidunt arcu, at sagittis eros nibh at lorem. Vivamus vitae pulvinar mi, ac vestibulum nulla. In eget arcu enim. Fusce egestas ullamcorper risus, non pellentesque mauris feugiat at."+
                "    </p>" +
                "</div>";
        TextView tv = (TextView) findViewById(R.id.twxt);
        tv.setText((new HtmlSpanner()).fromHtml(html));
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_view_data_binding) {
            startActivity(new Intent(this, DataBindingExampleActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
