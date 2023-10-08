package com.test.a7minworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.a7minworkout.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    var binding: ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnFinish?.setOnClickListener {
            finish()
        }
    }
}