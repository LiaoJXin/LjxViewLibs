package com.ljx.view.custom2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.ljx.view.custom1.R

//渐变色背景
@SuppressLint("Recycle")
class GradientButton: AppCompatTextView {

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
            val attributes = context.obtainStyledAttributes(it, R.styleable.GradientButton)
            val corners = attributes.getDimensionPixelSize(R.styleable.GradientButton_gradientBtnCorners,8)
            val startColor = attributes.getColor(R.styleable.GradientButton_startColor,0)
            val endColor = attributes.getColor(R.styleable.GradientButton_endColor,0)
            setBtnBackground(corners, startColor, endColor)
            attributes.recycle()
        }
    }

    private fun setBtnBackground(corners: Int, startColor: Int, endColor: Int) {
        if(startColor == 0 || endColor == 0) return;
        val gradientBg = GradientDrawable()
        gradientBg.shape = GradientDrawable.RECTANGLE
        gradientBg.cornerRadius = corners.toFloat()
        val colorArray = intArrayOf(startColor, endColor)
        gradientBg.colors = colorArray
        gradientBg.gradientType = GradientDrawable.LINEAR_GRADIENT //设置线性渐变
        gradientBg.orientation = GradientDrawable.Orientation.LEFT_RIGHT
        gradientBg.setDither(true) //防止抖动
        gradientBg.mutate()
        background = gradientBg//设置渐变色背景
    }

}

