package net.nightwhistler.htmlspanner.spans;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.style.LineHeightSpan;
import android.util.Log;

/**
 * Created by Salvatore on 06/12/2017.
 */

public class LineHeightSpanImpl implements LineHeightSpan {

    //private final int value;
    private int mSize;
    private static float sProportion = 0;
    private int ascent=0;
    private int descent=0;


    public LineHeightSpanImpl(int value) {
        //this.value = value;
        mSize=value;
    }

    @Override
    public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v,
                             Paint.FontMetricsInt fm) {

        Log.i("fmAscent",""+fm.ascent);
        Log.i("fmDescent",""+fm.descent);
        Log.i("fmTop",""+fm.top);
        Log.i("fmBottom",""+fm.bottom);

        if(ascent==0&&descent==0){

            int top = Math.abs(fm.top)-Math.abs(fm.ascent);
            int bottom= Math.abs(fm.bottom)-Math.abs(fm.descent);
            int lineHeight = Math.abs(fm.ascent)-fm.descent;

            mSize = mSize-lineHeight > 0 ? mSize-lineHeight : 0;

            Log.i("mSize",""+mSize);

            ascent=fm.top -= mSize/2;
            descent=fm.bottom += mSize/2;

            fm.top -= top;
            fm.bottom += bottom;
        }

        if(mSize > 0) {
            fm.ascent = ascent;
            fm.descent = descent;
        }
    }

}
