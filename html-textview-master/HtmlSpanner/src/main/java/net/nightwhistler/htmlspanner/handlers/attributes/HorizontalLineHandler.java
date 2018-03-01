package net.nightwhistler.htmlspanner.handlers.attributes;

import android.text.SpannableStringBuilder;
import android.util.Log;

import net.nightwhistler.htmlspanner.SpanStack;
import net.nightwhistler.htmlspanner.handlers.StyledTextHandler;
import net.nightwhistler.htmlspanner.spans.BorderSpan;
import net.nightwhistler.htmlspanner.spans.HorizontalLineSpan;
import net.nightwhistler.htmlspanner.style.Style;

import org.htmlcleaner.TagNode;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 6/23/13
 * Time: 3:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class HorizontalLineHandler extends WrappingStyleHandler {

    public HorizontalLineHandler(StyledTextHandler handler) {
        super(handler);
    }

    @Override
    public void handleTagNode(TagNode node, SpannableStringBuilder builder, int start, int end,
                              Style useStyle, SpanStack spanStack) {

        end+=1;
        Log.d("HorizontalLineHandler", "Draw hr from " + start + " to " + end);
        spanStack.pushSpan(new HorizontalLineSpan(useStyle, start, end), start, end);
        appendNewLine(builder);

        super.handleTagNode(node, builder, start, end, useStyle, spanStack);

    }


}
