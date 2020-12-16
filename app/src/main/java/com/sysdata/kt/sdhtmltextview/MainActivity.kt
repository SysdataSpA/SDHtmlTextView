package com.sysdata.kt.sdhtmltextview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.htmlText="<table border = \"1\">" +
                "<tr>" +
                "<th>Firstname</th>" +
                "<th>Lastname</th> " +
                "<th>Age</th>" +
                "</tr>" +
                "<tr>" +
                "<td>Jill</td>" +
                "<td>Smith</td>" +
                "<td>50</td>" +
                "</tr>" +
                "<tr>" +
                "<td>Eve</td>" +
                "<td>Jackson</td>" +
                "<td>94</td>" +
                "</tr>" +
                "<tr>" +
                "<td>John</td>" +
                "<td>Doe</td>" +
                "<td>80</td>" +
                "</tr>" +
                "</table>" +
                "<span style=\"margin-left: 50px\">"+
                "<ul>" +
                "  <li><span style=\"margin-left: 50px\">Coffee</span></li>" +
                "  <li><span style=\"margin-left: 50px\">Tea</span></li>" +
                "  <li><span style=\"margin-left: 50px\">Milk</span></li>" +
                "</ul>" +
                "</span>"+
                "<table>" +
                "<tr>" +
                "<th>Firstname</th>" +
                "<th>Lastname</th> " +
                "<th>Age</th>" +
                "</tr>" +
                "<tr>" +
                "<td>Jill</td>" +
                "<td>Smith</td>" +
                "<td>50</td>" +
                "</tr>" +
                "<tr>" +
                "<td>Eve</td>" +
                "<td>Jackson</td>" +
                "<td>94</td>" +
                "</tr>" +
                "<tr>" +
                "<td>John</td>" +
                "<td>Doe</td>" +
                "<td>80</td>" +
                "</tr>" +
                "</table>" +
                "<h1>The line-height Property</h1>" +
                "<h2>line-height: normal (default):</h2>" +
                "<div>This is a paragraph with a standard line-height.<br>" +
                "The standard line height in most browsers is about 110% to 120%.</div>" +
                "<h2>line-height: 1.6 (recommended):</h2>" +
                "<div style=\"line-height: 1.6 em\">This is a paragraph with the recommended line-height.<br>" +
                "The line height is here set to 1.6. This is a unitless value;<br>" +
                "meaning that the line height will be relative to the font size.</div>" +
                "<h2>line-height: 80%:</h2>" +
                "<div style=\"line-height: 80 %;\">This is a paragraph with a smaller line-height.<br>" +
                "The line height is here set to 80%.</div>" +
                "<h2>line-height: 50 px:</h2>" +
                "<div style=\"line-height: 50 px;\">" +
                "This is a paragraph with a bigger line-height.<br>" +
                "The line height is here set to 200%.</div>"
    }
}
