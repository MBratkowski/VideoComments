package io.thecapitals.videocomments.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View

/**
 * Created for project VideoComments on 06/10/2017.
 */

class SeekbarCommentIndicators : View {

    private val dotPaint: Paint = Paint()
    private val dotRadius = 15f

    private val dotsPositions: ArrayList<Int> = ArrayList()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    fun setDots(dots: Collection<Int>) {
        dotsPositions.clear()
        dotsPositions.addAll(dots)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width > 0) {
            dotsPositions.forEach {
                canvas.drawCircle(
                        paddingLeft - dotRadius + (width / 100f) * it,
                        height / 2f,
                        dotRadius, dotPaint)
            }
        }
    }

    private fun init() {
        dotPaint.color = Color.WHITE
        dotPaint.isAntiAlias = true
    }

}
