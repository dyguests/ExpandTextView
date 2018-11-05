package com.fanhl.expandtextview

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.text.Layout
import android.util.AttributeSet
import android.view.View
import android.widget.TextView

/**
 * 可以展开收起的TextView
 *
 * @author fanhl
 */
class ExpandTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var mOriginText: CharSequence? = null

    /**
     * 收到状态下可以显示的行数
     */
//    var collapsedLines = 2
    var mMaxLines = 2

    var ellipsizeText: CharSequence = "..."

    /**
     * 收起时的提示文字
     */
    var collapsedText: CharSequence? = "展开"

    /**
     * 展开时的提示文字
     */
    var expandedText: CharSequence? = "收起"

    private var mIsExactlyMode: Boolean = false
    /**
     * 控制是否更新text
     */
    private var mEnableUpdateOriginText = true

    /**
     * 当前状态
     *
     * true：展开，false：收起
     */
    var expanded = false

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView, defStyleAttr, R.style.Widget_ExpandTextView)

        // FIXME: 2018/11/5 fanhl

        a.recycle()
    }

    override fun setMaxLines(maxLines: Int) {
        if (mMaxLines != maxLines) {
            super.setMaxLines(maxLines)
            this.mMaxLines = maxLines
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        text = mOriginText
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        try {
            mIsExactlyMode = View.MeasureSpec.getMode(widthMeasureSpec) == View.MeasureSpec.EXACTLY
            val layout = layout
            if (layout != null) {
                if (isExceedMaxLine(layout) || isOutOfBounds(layout)) {
                    adjustEllipsizeEndText(layout)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun setText(text: CharSequence, type: TextView.BufferType) {
        if (mEnableUpdateOriginText) {
            mOriginText = text
        }

        super.setText(text, type)

        if (mIsExactlyMode) {
            requestLayout()
        }
    }

    /**
     * 是否超过设置的最大行数
     */
    private fun isExceedMaxLine(layout: Layout): Boolean {
        return layout.lineCount > mMaxLines && mMaxLines > 0
    }

    private fun isOutOfBounds(layout: Layout): Boolean {
        return layout.height > measuredHeight - paddingBottom - paddingTop
    }

    private fun adjustEllipsizeEndText(layout: Layout) {
//        val originText = mOriginText
//        val restSuffixText = originText.subSequence(
//            originText.length - mEllipsizeIndex, originText.length
//        )
//
//        val width = layout.width - paddingLeft - paddingRight
//        val maxLineCount = Math.max(1, computeMaxLineCount(layout))
//        val lastLineWidth = layout.getLineWidth(maxLineCount - 1).toInt()
//        val mLastCharacterIndex = layout.getLineEnd(maxLineCount - 1)
//
//        val suffixWidth = (Layout.getDesiredWidth(mEllipsizeText, paint) + Layout.getDesiredWidth(restSuffixText, paint)).toInt() + 1
//
//        mEnableUpdateOriginText = false
//        if (lastLineWidth + suffixWidth > width) {
//            val widthDiff = lastLineWidth + suffixWidth - width
//
//            val removedCharacterCount = computeRemovedEllipsizeEndCharacterCount(
//                widthDiff,
//                originText.subSequence(0, mLastCharacterIndex)
//            )
//
//            text = originText.subSequence(0, mLastCharacterIndex - removedCharacterCount)
//            append(mEllipsizeText)
//            append(restSuffixText)
//        } else {
//            text = originText.subSequence(0, mLastCharacterIndex)
//            append(mEllipsizeText)
//            append(restSuffixText)
//        }
//
//        mEnableUpdateOriginText = true
    }
}