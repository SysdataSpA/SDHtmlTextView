package com.sysdata.kt.htmltextview

import android.content.Context
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.widget.TextView
import com.sysdata.htmlspanner.HtmlSpanner

class SDHtmlTextView: TextView {

    constructor(context: Context?):this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        if(attrs != null) {
            context?.let { ctx ->
                val a = ctx.theme.obtainStyledAttributes(
                        attrs,
                        R.styleable.SDHtmlTextView,
                        defStyleAttr, 0)

                isTableHeaderCentered = a.getBoolean(R.styleable.SDHtmlTextView_tableHeaderCentered, true)

                a.recycle()
            }
        }
        this.movementMethod = LinkMovementMethod.getInstance()

        htmlSpanner = HtmlSpanner.Builder()
                .textColor(textColors.defaultColor)
                .textSize(textSize)
                .backgroundColor(solidColor)
                .tableHeaderCenter(isTableHeaderCentered)
                .build()
    }

    private var htmlSpanner: HtmlSpanner
    public var isTableHeaderCentered: Boolean = true

    var htmlText:String? = null
    set(value) {
        text = htmlSpanner.fromHtml(value)
    }

}