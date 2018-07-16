package com.sysdata.htmlspanner.handlers.attributes;

import android.text.SpannableStringBuilder;
import com.sysdata.htmlspanner.HtmlSpanner;
import com.sysdata.htmlspanner.SpanStack;
import com.sysdata.htmlspanner.style.Style;
import com.sysdata.htmlspanner.handlers.StyledTextHandler;
import org.htmlcleaner.TagNode;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 5/6/13
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class WrappingStyleHandler extends StyledTextHandler {

    private StyledTextHandler wrappedHandler;

    public WrappingStyleHandler(StyledTextHandler wrappedHandler) {
        super(new Style());
        this.wrappedHandler = wrappedHandler;
    }

    @Override
    public Style getStyle() {
        return wrappedHandler.getStyle();
    }

    @Override
    public void beforeChildren(TagNode node, SpannableStringBuilder builder, SpanStack spanStack) {
        if ( wrappedHandler != null ) {
            wrappedHandler.beforeChildren(node, builder, spanStack);
        }
    }

    @Override
    public void handleTagNode(TagNode node, SpannableStringBuilder builder, int start, int end, Style useStyle,
        SpanStack spanStack ) {
        if ( wrappedHandler != null ) {
            wrappedHandler.handleTagNode(node, builder, start, end, useStyle, spanStack);
        }
    }

    public StyledTextHandler getWrappedHandler() {
        return this.wrappedHandler;
    }

    @Override
    public void setSpanner(HtmlSpanner spanner) {
        super.setSpanner(spanner);

        if ( this.getWrappedHandler() != null ) {
            this.getWrappedHandler().setSpanner(spanner);
        }
    }

}
