package com.ljx.view.custom2

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ljx.view.custom1.R

//起始位置和终点位置
@SuppressLint("Recycle", "SetTextI18n")
class StartEndPlaceLayout: LinearLayout {

    private lateinit var tvStart: TextView
    private lateinit var tvEnd: TextView
    private lateinit var ivArrow: ImageView

    constructor(context: Context): super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.layout_start_end_place, this)
        tvStart = findViewById(R.id.tv_start)
        tvEnd = findViewById(R.id.tv_end)
        ivArrow = findViewById(R.id.iv_arrow)
        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.StartEndPlaceLayout)
            val startPlace = attributes.getString(R.styleable.StartEndPlaceLayout_startPlace)
            val endPlace = attributes.getString(R.styleable.StartEndPlaceLayout_endPlace)
            val txtSize = attributes.getDimensionPixelSize(R.styleable.StartEndPlaceLayout_txtSize, 14)
            val txtColor = attributes.getColor(R.styleable.StartEndPlaceLayout_txtColor, 0x212121)
            val arrowColor = attributes.getColor(R.styleable.StartEndPlaceLayout_arrowColor, 0x212121)
            val limitSize = attributes.getInteger(R.styleable.StartEndPlaceLayout_limitSize, 0)
            if(!startPlace.isNullOrEmpty()) tvStart.text = startPlace
            if(!endPlace.isNullOrEmpty()) tvEnd.text = endPlace
            tvStart.textSize = txtSize.toFloat()
            tvEnd.textSize = txtSize.toFloat()
            tvStart.setTextColor(txtColor)
            tvEnd.setTextColor(txtColor)
            ivArrow.setColorFilter(arrowColor)
            if(limitSize > 0) {
                if(!tvStart.text.isNullOrEmpty() && tvStart.text.length > limitSize) {
                    val limitStr = tvStart.text.subSequence(0,limitSize)
                    tvStart.text = "${limitStr}..."
                }
                if(!tvEnd.text.isNullOrEmpty() && tvEnd.text.length > limitSize) {
                    val limitStr = tvEnd.text.subSequence(0,limitSize)
                    tvEnd.text = "${limitStr}..."
                }
            }
        }
    }

    fun setStartPlace(start: String) {
        if(!start.isNullOrEmpty()) tvStart.text = start
    }

    fun setEndPlace(end: String) {
        if(!end.isNullOrEmpty()) tvEnd.text = end
    }

    fun setTextSize(size: Int) {
        tvStart.textSize = size.toFloat()
        tvEnd.textSize = size.toFloat()
    }

    fun setTextColor(color: Int) {
        tvStart.setTextColor(color)
        tvEnd.setTextColor(color)
    }

    fun setArrowColor(color: Int) {
        ivArrow.setColorFilter(color)
    }

    fun setPlaceLengthLimit(limit: Int) {
        if(limit > 0) {
            if(!tvStart.text.isNullOrEmpty() && tvStart.text.length > limit) {
                val limitStr = tvStart.text.subSequence(0,limit)
                tvStart.text = "${limitStr}..."
            }
            if(!tvEnd.text.isNullOrEmpty() && tvEnd.text.length > limit) {
                val limitStr = tvEnd.text.subSequence(0,limit)
                tvEnd.text = "${limitStr}..."
            }
        }
    }

}