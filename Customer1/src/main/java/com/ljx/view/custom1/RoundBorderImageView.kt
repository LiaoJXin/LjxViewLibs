package com.ljx.view.custom1

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import kotlin.math.min

//圆形边线图片控件
class RoundBorderImageView : View {

    private var borderColor = Color.GREEN

    //图像宽高
    private var bitmapWidth = 0
    private var bitmapHeight = 0
    //画圆图时半径位置
    private var drawableRadius = 0f
    //画边框时半径位置
    private var borderRadius = 0f
    private var roundDrawable: Drawable? = null
    //边框宽度
    private var borderWidth = 1f
    //着色器，画圆图关键
    private var bitmapShader: BitmapShader? = null
    //画圆图的画笔
    private lateinit var bitmapPaint: Paint
    //画边框的画笔
    private lateinit var borderPaint: Paint

    constructor(context: Context) : super(context) {
        initView(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defaultStyle: Int) : super(context, attrs, defaultStyle) {
        initView(attrs, defaultStyle)
    }

    private fun initView(attrs: AttributeSet?, defaultStyle: Int) {
        val attr =
            context.obtainStyledAttributes(attrs, R.styleable.RoundBorderImageView, defaultStyle, 0)
        val defaultColor = ContextCompat.getColor(context, R.color.color0E6CCB)
        borderColor = attr.getColor(R.styleable.RoundBorderImageView_roundBorderColor, defaultColor)
        borderWidth = attr.getDimension(R.styleable.RoundBorderImageView_roundBorderWidth, 1f)
        if (attr.hasValue(R.styleable.RoundBorderImageView_roundDrawable)) {
            roundDrawable = attr.getDrawable(R.styleable.RoundBorderImageView_roundDrawable)
        }
        attr.recycle()//回收释放
        roundDrawable?.let {
            val bitmap = getBmpFromDraw(it)
            //保存图像宽高
            bitmapWidth = bitmap.width
            bitmapHeight = bitmap.height
            //创建着色器，第二和第三参数指明图像的平铺模式
            bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            //创建位图画笔
            bitmapPaint = Paint()
            //设置着色器
            bitmapPaint.setShader(bitmapShader)
            //创建边框画笔
            borderPaint = Paint()
            //只画线不填充
            borderPaint.style = Paint.Style.STROKE
            //画边框添加平滑效果
            borderPaint.flags = Paint.ANTI_ALIAS_FLAG
            borderPaint.setColor(borderColor)
            borderPaint.strokeWidth = borderWidth
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            //画边框
            it.drawCircle(width/2f, height/2f, borderRadius, borderPaint)
            //画图像
            it.drawCircle(width/2f, height/2f, drawableRadius, bitmapPaint)
        }
    }

    private fun getBmpFromDraw(draw: Drawable): Bitmap {
        if (draw is BitmapDrawable) return draw.bitmap
        try {
            var bitmap: Bitmap
            if (draw is ColorDrawable) {
                //如果是颜色则创建一个宽高都是一像素的Bitmap，指定其颜色空间是ARGB四通道，每个通道占8个字节
                bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
            } else {
                //如果是其他类型的drawable，则创建一个与其同样大小的位图
                bitmap = Bitmap.createBitmap(draw.intrinsicWidth, draw.intrinsicHeight, Bitmap.Config.ARGB_8888)
            }
            //位图中必须有drawable中图案，所以用位图创建画布，把drawable画到画布上，实际上就画到了位图上
            val canvas = Canvas(bitmap)
            //设置绘图区域，绘制不好超过此区域
            draw.setBounds(0, 0, canvas.width, canvas.height)
            draw.draw(canvas)
            return bitmap
        } catch (e: Exception) {
            return null!!
        }
    }

    //计算位图的变换矩阵
    private fun updateShaderMatrix() {
        //获取控件上下左右padding，用于计算内容所处的区域
        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom
        //计算边框半径，按控件最外围来画
        borderRadius = min((height-borderWidth)/2f, (width-borderWidth)/2f)
        //位图所在的外围框，位图不能超过这个矩形
        //这个矩形所在控件的边框内，同时要考虑padding大小
        val drawRect = RectF(borderWidth + paddingLeft, borderWidth + paddingTop,
            width - borderWidth - paddingRight, height - borderWidth - paddingBottom)
        //计算画圆形位图所用半径
        drawableRadius = min(drawRect.height()/2f, drawRect.width()/2f)
        var scale = 0f
        //图像在x轴和y轴上开始的位置
        var dx = 0f
        var dy = 0f
        //计算图像的缩放和位移
        val mShaderMatrix = Matrix()
        mShaderMatrix.set(null)
        //计算图像需要缩放的比例
        if(bitmapWidth * drawRect.height() < bitmapHeight * drawRect.width()) {
            scale = drawRect.height() / bitmapHeight.toFloat()
            dx = (drawRect.width() - bitmapWidth * scale) * 0.5f
        } else {
            scale = drawRect.width() / bitmapWidth.toFloat()
            dy = (drawRect.height() - bitmapHeight * scale) * 0.5f
        }
        //设置x轴和y轴的缩放比例
        mShaderMatrix.setScale(scale, scale)
        //设置位置在x轴和y轴的位移，保证图像居中
        mShaderMatrix.postTranslate((dx + 0.5f).toInt() + borderWidth, (dy + 0.5f).toInt() + borderWidth)
        //将变化矩阵设置给着色器
        bitmapShader?.setLocalMatrix(mShaderMatrix)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateShaderMatrix()
    }

}