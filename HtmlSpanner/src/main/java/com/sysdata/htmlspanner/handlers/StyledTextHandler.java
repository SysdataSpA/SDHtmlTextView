package com.sysdata.htmlspanner.handlers;

import android.text.SpannableStringBuilder;
import android.util.Log;
import com.sysdata.htmlspanner.SpanStack;
import com.sysdata.htmlspanner.TagNodeHandler;

import com.sysdata.htmlspanner.spans.VerticalMarginSpan;
import com.sysdata.htmlspanner.style.Style;
import com.sysdata.htmlspanner.style.StyleCallback;
import com.sysdata.htmlspanner.style.StyleValue;
import org.htmlcleaner.TagNode;

/**
 * TagNodeHandler for any type of text that may be styled using CSS.
 *
 * @author Alex Kuiper
 */
public class StyledTextHandler extends TagNodeHandler {

    private Style style;

    public StyledTextHandler() {
        this.style = new Style();
    }

    public StyledTextHandler(Style style) {
        this.style = style;
    }

    public Style getStyle() {
        style.setFontFamily(getSpanner().getFont("sans-serif"));
        return style;
    }

    @Override
    public void beforeChildren(TagNode node, SpannableStringBuilder builder, SpanStack spanStack) {

        Style useStyle = spanStack.getStyle( node, getStyle() );

        if (builder.length() > 0 &&  useStyle.getDisplayStyle() == Style.DisplayStyle.BLOCK ) {

            if ( builder.charAt(builder.length() -1) != '\n' ) {
                builder.append('\n');
            }
        }

        //If we have a top margin, we insert an extra newline. We'll manipulate the line height
        //of this newline to create the margin.
        if ( useStyle.getMarginTop() != null ) {

            StyleValue styleValue = useStyle.getMarginTop();

            if ( styleValue.getUnit() == StyleValue.Unit.PX ) {
                if ( styleValue.getIntValue() > 0 ) {
                    if ( appendNewLine(builder) ) {
                        spanStack.pushSpan( new VerticalMarginSpan( styleValue.getIntValue() ),
                            builder.length() -1, builder.length() );
                    }
                }
            } else {
                if ( styleValue.getFloatValue() > 0f ) {
                    if ( appendNewLine(builder) ) {
                        spanStack.pushSpan( new VerticalMarginSpan( styleValue.getFloatValue() ),
                            builder.length() -1, builder.length() );
                    }
                }
            }

        }


    }

    public final void handleTagNode(TagNode node, SpannableStringBuilder builder,
                                    int start, int end, SpanStack spanStack) {
        Style styleFromCSS = spanStack.getStyle( node, getStyle() );
        handleTagNode(node, builder, start, end, styleFromCSS, spanStack);
    }

    public void handleTagNode(TagNode node, SpannableStringBuilder builder, int start, int end, Style useStyle, SpanStack stack ) {

        if ( useStyle.getDisplayStyle() == Style.DisplayStyle.BLOCK ) {
            appendNewLine(builder);

            //If we have a bottom margin, we insert an extra newline. We'll manipulate the line height
            //of this newline to create the margin.
            if ( useStyle.getMarginBottom() != null ) {

                StyleValue styleValue = useStyle.getMarginBottom();

                if ( styleValue.getUnit() == StyleValue.Unit.PX ) {
                    if ( styleValue.getIntValue() > 0 ) {
                        appendNewLine(builder);
                        stack.pushSpan( new VerticalMarginSpan( styleValue.getIntValue() ),
                            builder.length() -1, builder.length() );
                    }
                } else {
                    if ( styleValue.getFloatValue() > 0f ) {
                        appendNewLine(builder);

                        stack.pushSpan( new VerticalMarginSpan( styleValue.getFloatValue() ),
                            builder.length() -1, builder.length() );
                    }
                }

            }
        }

        //If we have a line height attribute, we apply it to the element
        if (useStyle.getLineHeight() != null && useStyle.getBackgroundColor()==null){
            StyleValue styleValue = useStyle.getLineHeight();
            if ( styleValue.getIntValue() > 0 ) {
                stack.pushSpan(new StyleCallback(getSpanner().getFontResolver()
                        .getDefaultFont(),useStyle,start,builder.length()));
            }
        }

        if ( builder.length() > start ) {
            stack.pushSpan(new StyleCallback(getSpanner().getFontResolver()
                .getDefaultFont(), useStyle, start, builder.length() ));
        } else {
            Log.d( "StyledTextHandler", "Refusing to push span of length " + ( builder.length() - start ));
        }
    }

}
