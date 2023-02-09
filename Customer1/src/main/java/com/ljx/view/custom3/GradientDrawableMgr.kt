package com.ljx.view.custom3

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable

/**
 * 封装GradientDrawable扩展，用于设置view的backgroundDrawable等场景
 * 解决多余的drawable占用资源空间过大的问题
 * https://www.cnblogs.com/guanxinjing/p/11142599.html
 * */
class GradientDrawableMgr {

    //伴生对象
    companion object {

        /**
         * 绘制实心圆角背景
         * @param solidColor 实心背景颜色
         * @param radius 角度
         * */
        fun solidCornersBg(solidColor: Int, radius: Int): GradientDrawable {
            val drawBg = GradientDrawable()
            drawBg.shape = GradientDrawable.RECTANGLE
            drawBg.cornerRadius = radius.toFloat()
            drawBg.setColor(solidColor)
            return drawBg
        }

        /**
         * 绘制实心圆角背景
         * @param solidColor 实心背景颜色
         * @param radius 角度
         * @param width
         * @param height
         * */
        fun solidCornersBg(solidColor: Int, width: Int, height: Int, radius: Int): GradientDrawable {
            val drawBg = GradientDrawable()
            drawBg.shape = GradientDrawable.RECTANGLE
            drawBg.cornerRadius = radius.toFloat()
            drawBg.setColor(solidColor)
            drawBg.setSize(width, height)
            return drawBg
        }

        /**
         * 绘制实心环线圆角背景
         * */
        fun solidStrokeCornersBg(solidColor: Int, strokeColor: Int, strokeWidth: Int, radius: Int): GradientDrawable {
            val draw = GradientDrawable()
            draw.shape = GradientDrawable.RECTANGLE
            draw.cornerRadius = radius.toFloat()
            draw.setStroke(strokeWidth, strokeColor)
            draw.setColor(solidColor)
            draw.mutate()
            return draw
        }

        fun solidStrokeCornersBg(solidColor: Int, strokeColor: Int, strokeWidth: Int, width: Int, height: Int,
                                 radius: Int): GradientDrawable {
            val drawBg = GradientDrawable()
            drawBg.shape = GradientDrawable.RECTANGLE
            drawBg.cornerRadius = radius.toFloat()
            drawBg.setStroke(strokeWidth, strokeColor)
            drawBg.setColor(solidColor)
            drawBg.setSize(width, height)
            drawBg.mutate()
            return drawBg
        }

        /**
         * 绘制不规则角度的实心圆角背景
         * @param topLeftRadius 左上角的角度
         * @param topRightRadius 右上角的角度
         * @param bottomRightRadius 右下角的角度
         * @param bottomLeftRadius 左下角的角度
         */
        fun solidIrregularBg(solidColor: Int, topLeftRadius: Int, topRightRadius: Int, bottomRightRadius: Int,
                             bottomLeftRadius: Int): GradientDrawable {
            val drawBg = GradientDrawable()
            drawBg.shape = GradientDrawable.RECTANGLE
            val radius = floatArrayOf(
                topLeftRadius.toFloat(),
                topLeftRadius.toFloat(),
                topRightRadius.toFloat(),
                topRightRadius.toFloat(),
                bottomRightRadius.toFloat(),
                bottomRightRadius.toFloat(),
                bottomLeftRadius.toFloat(),
                bottomLeftRadius.toFloat()
            )
            drawBg.cornerRadii = radius
            drawBg.setColor(solidColor)
            drawBg.mutate()
            return drawBg
        }

        /**
         * 绘制不规则角度的实心圆角背景
         * @param width 背景宽度
         * @param height 背景高度
         * @param topLeftRadius 左上角的角度
         * @param topRightRadius 右上角的角度
         * @param bottomRightRadius 右下角的角度
         * @param bottomLeftRadius 左下角的角度
         */
        fun solidIrregularBg(solidColor: Int, width: Int, height: Int, topLeftRadius: Int, topRightRadius: Int,
                             bottomRightRadius: Int, bottomLeftRadius: Int): GradientDrawable {
            val drawBg = GradientDrawable()
            drawBg.shape = GradientDrawable.RECTANGLE
            val radius = floatArrayOf(
                topLeftRadius.toFloat(),
                topLeftRadius.toFloat(),
                topRightRadius.toFloat(),
                topRightRadius.toFloat(),
                bottomRightRadius.toFloat(),
                bottomRightRadius.toFloat(),
                bottomLeftRadius.toFloat(),
                bottomLeftRadius.toFloat()
            )
            drawBg.cornerRadii = radius
            drawBg.setColor(solidColor)
            drawBg.setSize(width, height)
            drawBg.mutate()
            return drawBg
        }

        /**
         * 绘制实心环线不规则圆角背景
         */
        fun solidStrokeIrregularBg(solidColor: Int, strokeColor: Int, strokeWidth: Int,
                                   topLeftRadius: Int, topRightRadius: Int, bottomRightRadius: Int,
                                   bottomLeftRadius: Int): GradientDrawable {
            val drawBg = GradientDrawable()
            drawBg.shape = GradientDrawable.RECTANGLE
            val radius = floatArrayOf(
                topLeftRadius.toFloat(),
                topLeftRadius.toFloat(),
                topRightRadius.toFloat(),
                topRightRadius.toFloat(),
                bottomRightRadius.toFloat(),
                bottomRightRadius.toFloat(),
                bottomLeftRadius.toFloat(),
                bottomLeftRadius.toFloat()
            )
            drawBg.cornerRadii = radius
            drawBg.setStroke(strokeWidth, strokeColor)
            drawBg.setColor(solidColor)
            drawBg.mutate()
            return drawBg
        }

        fun solidStrokeIrregularBg(solidColor: Int, strokeColor: Int, strokeWidth: Int, width: Int, height: Int,
                                   topLeftRadius: Int, topRightRadius: Int, bottomRightRadius: Int,
                                   bottomLeftRadius: Int): GradientDrawable {
            val drawBg = GradientDrawable()
            drawBg.shape = GradientDrawable.RECTANGLE
            val radius = floatArrayOf(
                topLeftRadius.toFloat(),
                topLeftRadius.toFloat(),
                topRightRadius.toFloat(),
                topRightRadius.toFloat(),
                bottomRightRadius.toFloat(),
                bottomRightRadius.toFloat(),
                bottomLeftRadius.toFloat(),
                bottomLeftRadius.toFloat()
            )
            drawBg.cornerRadii = radius
            drawBg.setStroke(strokeWidth, strokeColor)
            drawBg.setColor(solidColor)
            drawBg.setSize(width, height)
            drawBg.mutate()
            return drawBg
        }

        /**
         * 横实线
         * @param width 线的高度
         */
        fun solidHorizontalLine(color: Int, width: Int): GradientDrawable {
            val draw = GradientDrawable()
            draw.shape = GradientDrawable.LINE
            draw.setStroke(width, color)
            draw.mutate()
            return draw
        }

        /**
         * 实心圆背景
         */
        fun solidCircleBg(color: Int, width: Int): GradientDrawable {
            val draw = GradientDrawable()
            draw.shape = GradientDrawable.OVAL
            draw.setColor(color)
            draw.setSize(width, width)
            draw.mutate()
            return draw
        }

        /**
         * 实心环线圆背景
         */
        fun solidStrokeCircleBg(solidColor: Int, strokeColor: Int, circleWidth: Int,
                                strokeWidth: Int): GradientDrawable {
            val draw = GradientDrawable()
            draw.shape = GradientDrawable.OVAL
            draw.setColor(solidColor)
            draw.setStroke(strokeColor, strokeWidth)
            draw.setSize(circleWidth, circleWidth)
            draw.mutate()
            return draw
        }

        /**
         * 虚线矩形背景
         * @param dashWidth 虚线的长度（以像素为单位），设置为0可禁用虚线
         * @param dashGap 虚线之间的像素间距
         */
        @SuppressLint("WrongConstant")
        fun strokeRectangleBg(strokeColor: Int, strokeWidth: Int, dashWidth: Int,
                              dashGap: Int): GradientDrawable {
            val draw = GradientDrawable()
            draw.shape = GradientDrawable.LINEAR_GRADIENT
            draw.setStroke(strokeWidth, strokeColor, dashWidth.toFloat(), dashGap.toFloat())
            draw.mutate()
            return draw
        }

        /**
         * 颜色渐变矩形背景
         * @param colors 渐变颜色组
         * @param orientation 颜色渐变方向，如GradientDrawable.Orientation.RIGHT_LEFT
         */
        fun gradualRectangleBg(colors: IntArray, orientation: GradientDrawable.Orientation,
                               radius: Int): GradientDrawable {
            val draw = GradientDrawable()
            draw.cornerRadius = radius.toFloat()
            draw.shape = GradientDrawable.RECTANGLE
            draw.colors = colors //添加颜色组
            draw.gradientType = GradientDrawable.LINEAR_GRADIENT //设置线性渐变
            draw.orientation = orientation
            draw.setDither(true) //防止抖动
            draw.mutate()
            return draw
        }
    }

}