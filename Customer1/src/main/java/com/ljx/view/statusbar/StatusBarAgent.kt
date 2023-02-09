package com.ljx.view.statusbar

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

class StatusBarAgent {

    companion object {
        //Get alpha color
        fun calculateStatusBarColor(color: Int, alpha: Int): Int {
            val a = 1 - alpha / 255f
            var red = color shr 16 and 0xff
            var green = color shr 8 and 0xff
            var blue = color and 0xff
            red = (red * a + 0.5).toInt()
            green = (green * a + 0.5).toInt()
            blue = (blue * a + 0.5).toInt()
            return 0xff shl 24 or (red shl 16) or (green shl 8) or blue
        }

        /**
         * set statusBarColor
         * @param statusColor color
         * @param alpha       0 - 255
         */
        fun setStatusBarColor(activity: Activity, @ColorInt statusColor: Int, alpha: Int) {
            setStatusBarColor(activity, calculateStatusBarColor(statusColor, alpha))
        }

        fun setStatusBarColor(activity: Activity, @ColorInt statusColor: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                StatusBarLollipop.setStatusBarColor(activity, statusColor)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                StatusBarKitKat.setStatusBarColor(activity, statusColor)
            }
        }

        fun translucentStatusBar(activity: Activity) {
            translucentStatusBar(activity, false)
        }

        /**
         * change to full screen mode
         * @param hideStatusBarBackground hide status bar alpha Background when SDK > 21, true if hide it
         */
        fun translucentStatusBar(activity: Activity, hideStatusBarBackground: Boolean) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                StatusBarLollipop.translucentStatusBar(activity, hideStatusBarBackground)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                StatusBarKitKat.translucentStatusBar(activity)
            }
        }

        fun setStatusBarColorForCollapsingToolbar(activity: Activity, appBarLayout: AppBarLayout, collapsingToolbarLayout: CollapsingToolbarLayout,
                                                  toolbar: Toolbar, @ColorInt statusColor: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                StatusBarLollipop.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, statusColor)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                StatusBarKitKat.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, statusColor)
            }
        }

        fun changeToLightStatusBar(activity: Activity) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                return
            }
            if (activity == null) {
                return
            }
            val window = activity.window ?: return
            val decorView = window.decorView ?: return
            decorView.systemUiVisibility =
                decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        fun cancelLightStatusBar(activity: Activity) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                return
            }
            if (activity == null) {
                return
            }
            val window = activity.window ?: return
            val decorView = window.decorView ?: return
            decorView.systemUiVisibility =
                decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }

}