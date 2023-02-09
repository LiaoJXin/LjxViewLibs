package com.ljx.view.custom3

import android.R
import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable

/**
 * 封装selector扩展，用于设置带状态变化的drawable
 * 参考：https://www.jianshu.com/p/ab7ee1017d3c
 * https://blog.csdn.net/weixin_35867994/article/details/117539429
 * https://wenku.baidu.com/view/6aa65026a5c30c22590102020740be1e650eccb0.html
 * */
@SuppressLint("SuspiciousImport")
class SelectorDrawableMgr {

    companion object {

        /**
         * 选中和未选中selector
         * @param selectedDraw 选中状态的图片
         * @param unselectedDraw 未选中和默认的图片
         * */
        fun selectStateDrawable(selectedDraw: Drawable, unselectedDraw: Drawable): StateListDrawable {
            val selector = StateListDrawable()
            //注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
            //所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
            selector.addState(intArrayOf(R.attr.state_selected), selectedDraw)
            selector.addState(intArrayOf(-R.attr.state_selected), unselectedDraw)
            //没有任何状态时显示的图片，就设置空集合，默认状态
            selector.addState(intArrayOf(), unselectedDraw)
            return selector
        }

        /**
         * 打勾和未打勾selector
         * */
        fun checkStateDrawable(checkedDraw: Drawable, uncheckedDraw: Drawable): StateListDrawable {
            val selector = StateListDrawable()
            selector.addState(intArrayOf(R.attr.state_checked), checkedDraw)
            selector.addState(intArrayOf(-R.attr.state_checked), uncheckedDraw)
            selector.addState(intArrayOf(), uncheckedDraw)
            return selector
        }

        /**
         * 获取焦点和无焦点selector
         * */
        fun focusStateDrawable(focusedDraw: Drawable, unfocusedDraw: Drawable): StateListDrawable {
            val selector = StateListDrawable()
            selector.addState(intArrayOf(R.attr.state_focused), focusedDraw)
            selector.addState(intArrayOf(-R.attr.state_focused), unfocusedDraw)
            selector.addState(intArrayOf(), unfocusedDraw)
            return selector
        }

        /**
         * 可用和非可用selector
         * */
        fun enableStateDrawable(enableDraw: Drawable, disableDraw: Drawable): StateListDrawable {
            val selector = StateListDrawable()
            selector.addState(intArrayOf(R.attr.state_enabled), enableDraw)
            selector.addState(intArrayOf(-R.attr.state_enabled), disableDraw)
            selector.addState(intArrayOf(), disableDraw)
            return selector
        }

        /**
         * 按压和未按压selector
         * */
        fun pressStateDrawable(pressedDraw: Drawable, unpressedDraw: Drawable): StateListDrawable {
            val selector = StateListDrawable()
            selector.addState(intArrayOf(R.attr.state_pressed), pressedDraw)
            selector.addState(intArrayOf(-R.attr.state_pressed), unpressedDraw)
            selector.addState(intArrayOf(), unpressedDraw)
            return selector
        }

    }

}