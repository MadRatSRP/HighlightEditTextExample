package com.madrat.example

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.EditText


class HighlightEditText(context: Context?, a: AttributeSet?) : EditText(context, a) {
    private val lineBounds: Rect
    private val highlightPaint: Paint
    private var lineNumber = 0
    private var lineHighlightEnabled = true

    constructor(context: Context?) : this(context, null) {}

    init {
        lineBounds = Rect()
        highlightPaint = Paint()
        highlightPaint.color = HIGHLIGHTER_YELLOW
    }

    companion object {
        private const val HIGHLIGHTER_YELLOW = -0x770c0ceb
    }

    var lineHighlightColor: Int
        get() = highlightPaint.getColor()
        set(color) {
            highlightPaint.setColor(color)
            if (lineHighlightEnabled) {
                invalidate()
            }
        }

    override fun onDraw(canvas: Canvas) {
        if (lineHighlightEnabled) {
            lineNumber = layout.getLineForOffset(selectionStart)
            getLineBounds(lineNumber, lineBounds)
            canvas.drawRect(lineBounds, highlightPaint)
        }
        super.onDraw(canvas)
    }

    fun setLineHighlightEnabled(enabled: Boolean) {
        lineHighlightEnabled = enabled
        invalidate()
    }

    fun isLineHighlightEnabled(): Boolean {
        return lineHighlightEnabled
    }
}