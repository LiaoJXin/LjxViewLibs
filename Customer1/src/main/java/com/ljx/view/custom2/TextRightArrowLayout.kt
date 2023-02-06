package com.ljx.view.custom2

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ljx.view.custom1.R

//文字提示右箭头跳转布局
class TextRightArrowLayout: LinearLayout {

    private lateinit var itemLayout: LinearLayout
    private lateinit var tvTitle: TextView
    private lateinit var tvRedPoint: TextView
    private lateinit var tvHintMsg: TextView
    private lateinit var ivArrow: ImageView


    constructor(context: Context): super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        initView(context, attrs)
    }

    @SuppressLint("Recycle")
    private fun initView(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.layout_text_right_arrow, this)
        itemLayout = findViewById(R.id.item_layout)
        tvTitle = findViewById(R.id.tv_title)
        tvRedPoint = findViewById(R.id.tv_red_point)
        tvHintMsg = findViewById(R.id.tv_hint_msg)
        ivArrow = findViewById(R.id.iv_arrow)
        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.TextRightArrowLayout)
            val paddingTopBottom = attributes.getDimensionPixelSize(R.styleable.TextRightArrowLayout_padding_top_bottom, 15)
            val paddingLeftRight = attributes.getDimensionPixelSize(R.styleable.TextRightArrowLayout_padding_left_right, 15)
            val isShowRed = attributes.getBoolean(R.styleable.TextRightArrowLayout_isShowRed, false)
            val headTitle = attributes.getString(R.styleable.TextRightArrowLayout_headTitle)
            val headTitleTxSize = attributes.getDimensionPixelSize(R.styleable.TextRightArrowLayout_head_title_tx_size, 14)
            val headTitleTxColor = attributes.getColor(R.styleable.TextRightArrowLayout_head_title_tx_color, 0x212121)
            val hintTxt = attributes.getString(R.styleable.TextRightArrowLayout_hint_text)
            val hintTxSize = attributes.getDimensionPixelSize(R.styleable.TextRightArrowLayout_hint_text_tx_size, 13)
            val hintTxColor = attributes.getColor(R.styleable.TextRightArrowLayout_hint_text_tx_color, 0x6e6e6e)
            val arrowColor = attributes.getColor(R.styleable.TextRightArrowLayout_right_arrow_color, 0x212121)
            //设置padding
            itemLayout.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom)
            val viewState = if(isShowRed) View.VISIBLE else View.INVISIBLE
            tvRedPoint.visibility = viewState
            if(!headTitle.isNullOrEmpty()) tvTitle.text = headTitle
            tvTitle.textSize = headTitleTxSize.toFloat()
            tvTitle.setTextColor(headTitleTxColor)
            if(!hintTxt.isNullOrEmpty()) tvHintMsg.text = hintTxt
            tvHintMsg.textSize = hintTxSize.toFloat()
            tvHintMsg.setTextColor(hintTxColor)
            ivArrow.setColorFilter(arrowColor)
        }
    }

    fun setLayoutPadding(paddingTopBottom: Int, paddingLeftRight: Int) {
        itemLayout.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom)
    }

    fun setRedPointState(isShowRed: Boolean) {
        val viewState = if(isShowRed) View.VISIBLE else View.INVISIBLE
        tvRedPoint.visibility = viewState
    }

    fun setHeadTitle(title: String) {
        if(!title.isNullOrEmpty()) tvTitle.text = title
    }

    fun setHeadTitleTxSize(size: Int) {
        tvTitle.textSize = size.toFloat()
    }

    fun setHeadTitleTxColor(color: Int) {
        if(color > 0) tvTitle.setTextColor(color)
    }

    fun setHintText(text: String) {
        if(!text.isNullOrEmpty()) tvHintMsg.text = text
    }

    fun setHintTxSize(size: Int) {
        tvHintMsg.textSize = size.toFloat()
    }

    fun setHintTxColor(color: Int) {
        if(color > 0) tvHintMsg.setTextColor(color)
    }

    fun setArrowColor(color: Int) {
        if(color > 0) ivArrow.setColorFilter(color)
    }

}