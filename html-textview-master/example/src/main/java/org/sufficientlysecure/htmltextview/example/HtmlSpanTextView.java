package org.sufficientlysecure.htmltextview.example;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.text.Html;
import android.util.AttributeSet;

import net.nightwhistler.htmlspanner.HtmlSpanner;

import org.sufficientlysecure.htmltextview.ClickableTableSpan;
import org.sufficientlysecure.htmltextview.DrawTableLinkSpan;
import org.sufficientlysecure.htmltextview.HtmlTagHandler;
import org.sufficientlysecure.htmltextview.JellyBeanSpanFixTextView;
import org.sufficientlysecure.htmltextview.LocalLinkMovementMethod;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Salvatore on 07/12/2017.
 */

public class HtmlSpanTextView extends JellyBeanSpanFixTextView {

    public static final String TAG = "HtmlTextView";
    public static final boolean DEBUG = false;

    @Nullable
    private ClickableTableSpan clickableTableSpan;
    @Nullable
    private DrawTableLinkSpan drawTableLinkSpan;

    private boolean removeTrailingWhiteSpace = true;

    public HtmlSpanTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public HtmlSpanTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HtmlSpanTextView(Context context) {
        super(context);
    }

    /**
     * @see org.sufficientlysecure.htmltextview.HtmlTextView#setHtml(int)
     */
    public void setHtml(@RawRes int resId) {
        setHtml(resId, null);
    }

    /**
     * @see org.sufficientlysecure.htmltextview.HtmlTextView#setHtml(String)
     */
    public void setHtml(@NonNull String html) {
        setHtml(html, null);
    }

    /**
     * Loads HTML from a raw resource, i.e., a HTML file in res/raw/.
     * This allows translatable resource (e.g., res/raw-de/ for german).
     * The containing HTML is parsed to Android's Spannable format and then displayed.
     *
     * @param resId       for example: R.raw.help
     * @param imageGetter for fetching images. Possible ImageGetter provided by this library:
     *                    HtmlLocalImageGetter and HtmlRemoteImageGetter
     */
    public void setHtml(@RawRes int resId, @Nullable Html.ImageGetter imageGetter) {
        InputStream inputStreamText = getContext().getResources().openRawResource(resId);

        setHtml(convertStreamToString(inputStreamText), imageGetter);
    }

    /**
     * Parses String containing HTML to Android's Spannable format and displays it in this TextView.
     * Using the implementation of Html.ImageGetter provided.
     *
     * @param html        String containing HTML, for example: "<b>Hello world!</b>"
     * @param imageGetter for fetching images. Possible ImageGetter provided by this library:
     *                    HtmlLocalImageGetter and HtmlRemoteImageGetter
     */
    public void setHtml(@NonNull String html, @Nullable Html.ImageGetter imageGetter) {
        final HtmlTagHandler htmlTagHandler = new HtmlTagHandler(getPaint());
        htmlTagHandler.setClickableTableSpan(clickableTableSpan);
        htmlTagHandler.setDrawTableLinkSpan(drawTableLinkSpan);

        html = htmlTagHandler.overrideTags(html);

        if (removeTrailingWhiteSpace) {
            setText(removeHtmlBottomPadding(Html.fromHtml(html, imageGetter, htmlTagHandler)));
        } else {
            setText(Html.fromHtml(html, imageGetter, htmlTagHandler));
        }

        // make links work
        setMovementMethod(LocalLinkMovementMethod.getInstance());
    }

    /**
     * The Html.fromHtml method has the behavior of adding extra whitespace at the bottom
     * of the parsed HTML displayed in for example a TextView. In order to remove this
     * whitespace call this method before setting the text with setHtml on this TextView.
     *
     * @param removeTrailingWhiteSpace true if the whitespace rendered at the bottom of a TextView
     *                                 after setting HTML should be removed.
     */
    public void setRemoveTrailingWhiteSpace(boolean removeTrailingWhiteSpace) {
        this.removeTrailingWhiteSpace = removeTrailingWhiteSpace;
    }

    /**
     * The Html.fromHtml method has the behavior of adding extra whitespace at the bottom
     * of the parsed HTML displayed in for example a TextView. In order to remove this
     * whitespace call this method before setting the text with setHtml on this TextView.
     *
     * This method is deprecated, use setRemoveTrailingWhiteSpace instead.
     *
     * @param removeFromHtmlSpace true if the whitespace rendered at the bottom of a TextView
     *                            after setting HTML should be removed.
     */
    @Deprecated()
    public void setRemoveFromHtmlSpace(boolean removeFromHtmlSpace) {
        this.removeTrailingWhiteSpace = removeFromHtmlSpace;
    }

    public void setClickableTableSpan(@Nullable ClickableTableSpan clickableTableSpan) {
        this.clickableTableSpan = clickableTableSpan;
    }

    public void setDrawTableLinkSpan(@Nullable DrawTableLinkSpan drawTableLinkSpan) {
        this.drawTableLinkSpan = drawTableLinkSpan;
    }

    /**
     * http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
     */
    @NonNull
    static private String convertStreamToString(@NonNull InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    /**
     * Html.fromHtml sometimes adds extra space at the bottom.
     * This methods removes this space again.
     * See https://github.com/SufficientlySecure/html-textview/issues/19
     */
    @Nullable
    static private CharSequence removeHtmlBottomPadding(@Nullable CharSequence text) {
        if (text == null) {
            return null;
        }

        while (text.length() > 0 && text.charAt(text.length() - 1) == '\n') {
            text = text.subSequence(0, text.length() - 1);
        }
        return text;
    }
}
