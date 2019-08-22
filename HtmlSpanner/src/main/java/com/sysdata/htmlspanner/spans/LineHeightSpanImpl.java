package com.sysdata.htmlspanner.spans;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;
import android.util.Log;

import com.sysdata.htmlspanner.style.StyleValue;

/**
 * Created by Salvatore on 06/12/2017.
 */

public class LineHeightSpanImpl implements LineHeightSpan {

    private int mSize;


    public LineHeightSpanImpl(int value) {
        mSize=value;
    }

    public LineHeightSpanImpl(float value, StyleValue.Unit unit, Float textSize){
        switch (unit){
            case PX:
                mSize = (int) value;
                break;
            case PERCENTAGE:
            case EM:
                if(textSize != null && textSize > 0){
                    mSize = (int) (textSize * value);
                }
        }
    }

    @Override
    public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v,
                             Paint.FontMetricsInt fm) {

        Log.i("fmAscent",""+fm.ascent);
        Log.i("fmDescent",""+fm.descent);
        Log.i("fmTop",""+fm.top);
        Log.i("fmBottom",""+fm.bottom);

        if (fm.descent > mSize) {
            // Show as much descent as possible
            fm.bottom = fm.descent = Math.min(mSize, fm.descent);
            fm.top = fm.ascent = 0;
        } else if (-fm.ascent + fm.descent > mSize) {
            // Show all descent, and as much ascent as possible
            fm.bottom = fm.descent;
            fm.top = fm.ascent = -mSize + fm.descent;
        } else if (-fm.ascent + fm.bottom > mSize) {
            // Show all ascent, descent, as much bottom as possible
            fm.top = fm.ascent;
            fm.bottom = fm.ascent + mSize;
        } else if (-fm.top + fm.bottom > mSize) {
            // Show all ascent, descent, bottom, as much top as possible
            fm.top = fm.bottom - mSize;
        } else {
            // Show proportionally additional ascent / top & descent / bottom
            final int additional = mSize - (-fm.top + fm.bottom);

            // Round up for the negative values and down for the positive values  (arbritary choice)
            // So that bottom - top equals additional even if it's an odd number.
            fm.top -= Math.ceil(additional / 2.0f);
            fm.bottom += Math.floor(additional / 2.0f);
            fm.ascent = fm.top;
            fm.descent = fm.bottom;
        }
    }

}
