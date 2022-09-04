package com.pluu.touchable.sample

import android.graphics.Rect
import android.os.Bundle
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.core.view.updateLayoutParams
import com.pluu.touchable.sample.databinding.ActivitySample1Binding

class Sample1Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySample1Binding

    private val touchDelegateComposite: TouchDelegateComposite by lazy {
        TouchDelegateComposite(binding.root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySample1Binding.inflate(layoutInflater)
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
            binding.imageButton3.ripple(addHitSize)
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