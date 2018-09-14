package com.sysdata.kt.htmltextview

import android.content.Context
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.widget.TextView
import com.sysdata.htmlspanner.HtmlSpanner

class SDHtmlTextView: TextView {

    constructor(context: Context?):super(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    private var htmlSpanner: HtmlSpanner = HtmlSpanner(textColors.defaultColor, textSize)

    var htmlText:String? = null
    set(value) {
        text = htmlSpanner.fromHtml(value)
    }

    init {
        this.movementMethod = LinkMovementMethod.getInstance()
        htmlSpanner.setBackgroundColor(solidColor)
    }

}