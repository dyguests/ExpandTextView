package com.fanhl.expandtextview

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

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
    /**
     * 当前状态
     *
     * true：展开，false：收起
     */
    var expanded = false
    /**
     * 收到状态下可以显示的行数
     */
    var collapsedLines = 2

    var ellipsizeText: CharSequence = "..."

    var collapseText: CharSequence? = "收起"

    var expandText: CharSequence? = "展开"

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView, defStyleAttr, R.style.Widget_ExpandTextView)

        // FIXME: 2018/11/5 fanhl

        a.recycle()
    }
}