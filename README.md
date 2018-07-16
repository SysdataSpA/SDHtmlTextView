# SDHtmlTextView
Inspired by:
* HTMLTextView https://github.com/PrivacyApps/html-textview.
* HTMLSpanner https://github.com/NightWhistler/HtmlSpanner.

SDHtmlTextView use HTMLSpanner to display properly in TextViews an html page, overriding Html.fromHtml() and handling some CSS style inline properties.

### HTML Tags supported 

* ``<i>``
* ``<em>``
* ``<cite>``
* ``<dfn>``
* ``<b>``
* ``<strong>``
* ``<blockquote>``
* ``<ul>``
* ``<ol>``
* ``<tt>``
* ``<code>``
* ``<style>``
* ``<br>``
* ``<p>``
* ``<div>``
* ``<span>``
* ``<big>``
* ``<small>``
* ``<pre>``
* ``<sub>``
* ``<sup>``
* ``<center>``
* ``<li>``
* ``<font size="..." color="..." face="...">``
* ``<h1>``, ``<h2>``, ``<h3>``, ``<h4>``, ``<h5>``, ``<h6>``
* ``<a href="...">``
* ``<img src="...">``
* ``<table>``
* ``<u>``
* ``<hr/>``


### CSS Tags supported 

* ``color``
* ``background-color``
* ``align``
* ``text-align``
* ``font-weight``
* ``font-style``
* ``font-family``
* ``font-size``
* ``line-height`` only with measures in px 
* ``margin-bottom``
* ``margin-top``
* ``margin-left``
* ``margin-right``
* ``margin``
* ``text-indent``
* ``display``
* ``border-style``
* ``border-color``
* ``border-width``
* ``border``
* ``text-decoration`` only underline and line-through 

## Usage

1. **Add the library as a dependency**

1.1 in **Project level `build.gradle`** add those repositories
```gradle
   maven { url  'https://dl.bintray.com/sysdata/maven' }
   mavenCentral()
   maven { url 'http://repo.pageturner-reader.org' }
```
1.2 in your **App level `build.gradle`** add this dependecy
```gradle
    implementation 'it.sysdata.mobile:htmlspanner:1.0.0'
```

1.3 Use HtmlSpanner in your TextView :

In the xml layout file define a simple TextView then in the Activity do

```java
        String html=loadStringFromAssetFile(this,"example.html");
        TextView tv = (TextView) findViewById(R.id.text);
        int col=tv.getSolidColor();
        HtmlSpanner htmlSpanner=new HtmlSpanner();
        htmlSpanner.setBackgroundColor(col);
        tv.setText(htmlSpanner.fromHtml(html));
```
If you want to handle href you need to add
```java
        tv.setMovementMethod(LinkMovementMethod.getInstance());
```
You can also use an **SDHtmlTextView** instead of text view :

1. **Add the library as a dependency**

1.1 in **Project level `build.gradle`** add those repositories
```gradle
   maven { url  'https://dl.bintray.com/sysdata/maven' }
   mavenCentral()
   maven { url 'http://repo.pageturner-reader.org' }
```
1.2 in your **App level `build.gradle`** add this dependecy
```gradle
    implementation 'it.sysdata.mobile:htmltextview:1.0.0'
```
1.3 add the SDHtmlTextView via xml and then in your code set the html

**kotlin:**

```java
        String html=loadStringFromAssetFile(this,"example.html")
        htmlTextView.htmlText = html
```

**java:**

```java
        String html=loadStringFromAssetFile(this,"example.html");
        SDHtmlTextView htmlTextView = (SDHtmlTextView) findViewById(R.id.text);
        htmlTextView.setHtmlText(html);
```

## License
Copyright (C) 2017 Sysdata S.p.A.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


