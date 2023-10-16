package com.test.a7minworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.a7minworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.flStart?.setOnClickListener {
            val intentStart = Intent(this,ExerciseActivity::class.java)
            startActivity(intentStart)
        }

        binding?.flBmiCalculator?.setOnClickListener {
            val intentBMI = Intent(this,BMIActivity::class.java)
            startActivity(intentBMI)
        }

        binding?.flBodyFatCalculator?.setOnClickListener {
            val intentBodyFat = Intent(this,BodyFatActivity::class.java)
            startActivity(intentBodyFat)
        }

        binding?.flHistory?.setOnClickListener {
            val intentHistory = Intent(this,HistoryActivity::class.java)
            startActivity(intentHistory)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}