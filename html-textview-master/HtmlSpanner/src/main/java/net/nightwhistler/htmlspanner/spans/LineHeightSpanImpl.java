package net.nightwhistler.htmlspanner.spans;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;

/**
 * Created by Salvatore on 06/12/2017.
 */

public class LineHeightSpanImpl implements LineHeightSpan {

    private final int value;
    private int ascent,descent;

    public LineHeightSpanImpl(int value) {
        this.value = value;
        ascent=descent=0;
    }

    @Override
    public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v,
                             Paint.FontMetricsInt fm) {

        if(ascent==0&&descent==0){
            ascent=fm.ascent -= value/2;
            descent=fm.descent += value/2;
        }

        fm.ascent=ascent;
        fm.descent=descent;

    }

}
