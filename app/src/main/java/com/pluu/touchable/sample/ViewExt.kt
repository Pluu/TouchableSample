package com.pluu.touchable.sample

import android.content.res.Resources
import android.graphics.drawable.RippleDrawable
import android.widget.ImageButton
import androidx.core.view.doOnPreDraw

fun Int.dp2px(): Int {
    val scale: Float = Resources.getSystem().displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

/**
 * Change Ripple size
 * - Radius = (width + addSize * 2) / 2
 * @param addSize Append size
 */
fun ImageButton.ripple(
    addSize: Int
) {
    doOnPreDraw {
        val drawable = background
        if (drawable is RippleDrawable) {
            val lineSize = width + addSize * 2
            // 내접원 기준으로 Ripple
            drawable.radius = lineSize / 2
            // 외접원 기준으로 Ripple
            // drawable.radius = (lineSize / sqrt(2f)).toInt()
        }
        background = drawable
    }
}