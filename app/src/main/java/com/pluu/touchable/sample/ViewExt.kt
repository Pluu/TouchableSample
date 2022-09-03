package com.pluu.touchable.sample

import android.content.res.Resources

fun Int.dp2px(): Int {
    val scale: Float = Resources.getSystem().displayMetrics.density
    return (this * scale + 0.5f).toInt()
}