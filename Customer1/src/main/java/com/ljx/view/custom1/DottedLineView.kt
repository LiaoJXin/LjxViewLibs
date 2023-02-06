package com.ljx.view.custom1

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

//自定义方向虚线
class DottedLineView: View {

    val ORIENTATION_HORIZONTAL = 0
    val ORIENTATION_VERTICAL = 1
    private lateinit var mPaint: Paint
    private var orientation = 0

    constructor(context: Context): super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        initView(context, attrs)
    }

    @SuppressLint("Recycle")
    private fun initView(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.DottedLineView)
            val dashGap = attributes.getDimensionPixelSize(R.styleable.DottedLineView_dashGap, 5)
            val dashLength = attributes.getDimensionPixelSize(R.styleable.DottedLineView_dashLength, 5)
            val dashThickness = attributes.getDimensionPixelSize(R.styleable.DottedLineView_dashThickness, 3)
            val color = attributes.getColor(R.styleable.DottedLineView_divider_line_color, -0x1000000)
            orientation = attributes.getInt(R.styleable.DottedLineView_divider_orientation, ORIENTATION_HORIZONTAL)
            mPaint = Paint()
            mPaint.setAntiAlias(true)
            mPaint.setColor(color)
            mPaint.setStyle(Paint.Style.STROKE)
            mPaint.setStrokeWidth(dashThickness.toFloat())
            mPaint.setPathEffect(DashPathEffect(floatArrayOf(dashGap.toFloat(), dashLength.toFloat()), 0F))
        }
    }

    fun setBgColor(color: Int) {
        mPaint.color = color
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        if (orientation == ORIENTATION_HORIZONTAL) {
            val center = height * 0.5f
            canvas.drawLine(0f, center, width.toFloat(), center, mPaint)
        } else {
            val center = width * 0.5f
            canvas.drawLine(center, 0f, center, height.toFloat(), mPaint)
        }
    }

}