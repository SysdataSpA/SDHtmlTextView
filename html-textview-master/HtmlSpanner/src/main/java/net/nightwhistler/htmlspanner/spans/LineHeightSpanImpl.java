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
    private int lineHeight;
    private int ascent=0;
    private int descent=0;


    public LineHeightSpanImpl(int value,int lineHeight) {
        //this.value = value;
        mSize=value;
        this.lineHeight=lineHeight;
    }

    @Override
    public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v,
                             Paint.FontMetricsInt fm) {

        if(ascent==0&&descent==0){

            Log.i("sizeH",""+Math.abs(mSize));
            mSize-=lineHeight;
            Log.i("sizeH",""+Math.abs(mSize));
            Log.i("sizeT",""+lineHeight);

            mSize=Math.abs(mSize);
            ascent=fm.ascent -= mSize/2;
            descent=fm.descent += mSize/2;
        }

        fm.ascent=ascent;
        fm.descent=descent;
    }

}
