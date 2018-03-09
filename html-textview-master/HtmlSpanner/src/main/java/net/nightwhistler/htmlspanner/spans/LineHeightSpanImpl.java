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
