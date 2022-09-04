package com.pluu.touchable.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pluu.sample.button
import com.pluu.sample.column
import com.pluu.sample.setContentView
import com.pluu.utils.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView {
            column {
                button("Sample 1") {
                    startActivity<Sample1Activity>()
                }
                button("Sample 2") {
                    startActivity<Sample2Activity>()
                }
            }
        }
    }
}