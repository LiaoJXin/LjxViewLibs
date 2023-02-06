package com.ljx.view.custom2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.ljx.view.custom1.R

//带环线颜色按钮
@SuppressLint("Recycle")
class StrokeSolidButton: AppCompatTextView {

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.StrokeSolidButton)
            val strokeWidth = attributes.getDimensionPixelSize(R.styleable.StrokeSolidButton_strokeWidth, 1)
            val solidColor = attributes.getColor(R.styleable.StrokeSolidButton_solidColor, 0xffffff)
            val strokeColor = attributes.getColor(R.styleable.StrokeSolidButton_strokeColor, 0x0E6CCB)
            val corners = attributes.getDimensionPixelSize(R.styleable.StrokeSolidButton_strokeBtnCorners, 10)
            setBackground(strokeWidth, strokeColor, solidColor, corners)
            attributes.recycle()
        }
    }

    private fun setBackground(strokeWidth: Int, strokeColor: Int, solidColor: Int, corners: Int) {
        val gradientBg = GradientDrawable()
        gradientBg.shape = GradientDrawable.RECTANGLE
        gradientBg.cornerRadius = corners.toFloat()
        gradientBg.setStroke(strokeWidth, strokeColor)
        gradientBg.setColor(solidColor)
        gradientBg.mutate()
        background = gradientBg
    }

}