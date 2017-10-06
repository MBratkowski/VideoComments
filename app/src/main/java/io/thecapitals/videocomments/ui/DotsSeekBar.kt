package io.thecapitals.videocomments.ui

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.support.v7.widget.AppCompatSeekBar
import android.util.AttributeSet
import io.thecapitals.videocomments.R

class DotsSeekBar : AppCompatSeekBar {

    lateinit var dotsPositions: IntArray
    lateinit var dotBitmap: Bitmap

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    fun setDots(dots: IntArray) {
        dotsPositions = dots
        invalidate()
    }

    fun setDotsDrawable(dotResource: Int) {
        dotBitmap=BitmapFactory.decodeResource(resources,dotResource)
        invalidate()
    }

    private fun init(attrs: AttributeSet?) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.DotsSeekBar)
        initDots(typedArray)
        initDotBitmap(typedArray)
    }

    private fun initDots(typedArray: TypedArray) {
        val dotsArrayResources = typedArray.getResourceId(R.styleable.DotsSeekBar_dots_positions, 0)
        if (dotsArrayResources != 0) {
            dotsPositions = resources.getIntArray(dotsArrayResources)
        }
    }

    private fun initDotBitmap(typedArray: TypedArray) {
        val dotDrawableId = typedArray.getResourceId(R.styleable.DotsSeekBar_dots_drawables, 0)
        if (dotDrawableId != 0) {
            dotBitmap = BitmapFactory.decodeResource(resources, dotDrawableId)
        }
    }
}