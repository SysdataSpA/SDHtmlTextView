package com.sysdata.kt.sdhtmltextview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.htmlText="<div style=\"background-color: #272822\"><span style=\"color: #272822\">_</span><span style=\"color: #f8f8f2\">SDRootValidatorConfig</span> <span style=\"color: #f8f8f2\">config</span> <span style=\"color: #f92672\">=</span> <span style=\"color: #f8f8f2\">SDRootValidatorConfig</span>\n<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">newBuilder</span><span style=\"color: #f92672\">(</span><span style=\"color: #f8f8f2\">getContext</span><span style=\"color: #f92672\">())</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">isRootCheckEnabled</span><span style=\"color: #f92672\">(</span><span style=\"color: #66d9ef\">true</span><span style=\"color: #f92672\">)</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">checkForBinaryBusybox</span><span style=\"color: #f92672\">(</span><span style=\"color: #66d9ef\">true</span><span style=\"color: #f92672\">)</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">checkForBinarySu</span><span style=\"color: #f92672\">(</span><span style=\"color: #66d9ef\">true</span><span style=\"color: #f92672\">)</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">checkForDangerousProps</span><span style=\"color: #f92672\">(</span><span style=\"color: #66d9ef\">true</span><span style=\"color: #f92672\">)</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">checkForMagiskBinary</span><span style=\"color: #f92672\">(</span><span style=\"color: #66d9ef\">true</span><span style=\"color: #f92672\">)</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">checkForRootNative</span><span style=\"color: #f92672\">(</span><span style=\"color: #66d9ef\">true</span><span style=\"color: #f92672\">)</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">checkForRWPaths</span><span style=\"color: #f92672\">(</span><span style=\"color: #66d9ef\">true</span><span style=\"color: #f92672\">)</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">checkSuExists</span><span style=\"color: #f92672\">(</span><span style=\"color: #66d9ef\">true</span><span style=\"color: #f92672\">)</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">detectPotentiallyDangerousApps</span><span style=\"color: #f92672\">(</span><span style=\"color: #66d9ef\">true</span><span style=\"color: #f92672\">)</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">detectRootManagementApps</span><span style=\"color: #f92672\">(</span><span style=\"color: #66d9ef\">true</span><span style=\"color: #f92672\">)</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">detectTestKeys</span><span style=\"color: #f92672\">(</span><span style=\"color: #66d9ef\">true</span><span style=\"color: #f92672\">)</span>\n" +
                "<span style=\"color: #272822\">_</span><span style=\"color: #f92672\">.</span><span style=\"color: #a6e22e\">build</span><span style=\"color: #f92672\">();</span>\n" +
                "</div>\n"
    }
}
