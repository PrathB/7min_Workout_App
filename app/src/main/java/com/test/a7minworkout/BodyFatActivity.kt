package com.test.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.test.a7minworkout.databinding.ActivityBodyFatBinding

class BodyFatActivity : AppCompatActivity() {

    private var binding: ActivityBodyFatBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyFatBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.bodyFatActivityActionBar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.bodyFatActivityActionBar?.title = "CALCULATE BODY FAT %"

        binding?.bodyFatActivityActionBar?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.rbImperialUnits?.setOnClickListener {
//            the metric input field is made invisible and imperial made visible
            binding?.llMetricBodyFatInput?.visibility = View.INVISIBLE
            binding?.llImperialBodyFatInput?.visibility = View.VISIBLE
            binding?.llBmiValue?.visibility = View.INVISIBLE
        }

        binding?.rbMetricUnits?.setOnClickListener {
//            the imperial input field is made invisible and metric made visible
            binding?.llMetricBodyFatInput?.visibility = View.VISIBLE
            binding?.llImperialBodyFatInput?.visibility = View.INVISIBLE
            binding?.llBmiValue?.visibility = View.INVISIBLE
        }
    }
}