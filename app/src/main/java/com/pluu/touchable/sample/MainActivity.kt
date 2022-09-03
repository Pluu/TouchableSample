package com.pluu.touchable.sample

import android.graphics.Rect
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.core.view.updateLayoutParams
import com.pluu.touchable.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val touchDelegateComposite: TouchDelegateComposite by lazy {
        TouchDelegateComposite(binding.root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Expand Hit Size
        val addHitSize = 30.dp2px()

        setUpViews(
            binding.imageButton2 to binding.guideView2,
            binding.imageButton3 to binding.guideView3,
            addSize = addHitSize
        )

        // Change Ripple
        // Hit Rect 기준으로 ripple 사이즈를 결정
        binding.imageButton3.post {
            val drawable = binding.imageButton3.background
            if (drawable is RippleDrawable) {
                val lineSize = binding.imageButton3.width + addHitSize * 2
                // 내접원 기준으로 Ripple
                drawable.radius = lineSize / 2
                // 외접원 기준으로 Ripple
                // drawable.radius = (lineSize / sqrt(2f)).toInt()
            }
            binding.imageButton3.background = drawable
        }
    }

    private fun setUpViews(vararg targetView: Pair<View, View>, addSize: Int) {
        binding.root.doOnPreDraw { parent ->
            for ((view, guideView) in targetView) {
                val updateHitRect = Rect().also { r ->
                    view.getHitRect(r)
                    r.inset(-addSize, -addSize)
                }
                touchDelegateComposite.addDelegate(TouchDelegate(updateHitRect, view))

                // Update Guide location
                guideView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    width = updateHitRect.width()
                    height = updateHitRect.height()
                    leftMargin = -addSize
                    topMargin = -addSize
                }
                guideView.visibility = View.VISIBLE
            }
            parent.touchDelegate = touchDelegateComposite
        }
    }
}