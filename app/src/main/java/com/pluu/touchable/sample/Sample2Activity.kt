package com.pluu.touchable.sample

import android.graphics.Rect
import android.os.Bundle
import android.view.TouchDelegate
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import com.pluu.touchable.sample.databinding.ActivitySample2Binding

class Sample2Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySample2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySample2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
    }

    private fun setUpViews() {
        // Expand Hit Size
        val addHitSize = 12.dp2px() // 추가로 Hit하려는 크기 (기존 Padding으로 추가한 사이즈)
        val targetView = binding.changeButton
        val parentView = targetView.parent as View

        parentView.doOnPreDraw { parent ->
            val updateHitRect = Rect().also { r ->
                targetView.getHitRect(r)
                r.inset(-addHitSize, -addHitSize)
            }
            parent.touchDelegate = TouchDelegate(updateHitRect, targetView)
        }

        targetView.ripple(addHitSize + 8.dp2px())
    }
}