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

        binding?.bodyFatActivityActionBar?.title = "CALCULATE BODY FAT"

        binding?.bodyFatActivityActionBar?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.rbImperialUnits?.setOnClickListener {
//            the metric input field is made invisible and imperial made visible
            if(binding?.rbMale?.isChecked == true) {
                binding?.llMetricBodyFatInput?.visibility = View.INVISIBLE
                binding?.llImperialBodyFatInput?.visibility = View.VISIBLE

                binding?.textInputLayoutHipImperial?.visibility = View.INVISIBLE

                binding?.llBodyFatValue?.visibility = View.INVISIBLE
            }
            else{
                binding?.llMetricBodyFatInput?.visibility = View.INVISIBLE
                binding?.llImperialBodyFatInput?.visibility = View.VISIBLE

                binding?.textInputLayoutHipMetric?.visibility = View.INVISIBLE
                binding?.textInputLayoutHipImperial?.visibility = View.VISIBLE

                binding?.llBodyFatValue?.visibility = View.INVISIBLE
            }
        }

        binding?.rbMetricUnits?.setOnClickListener {
//            the imperial input field is made invisible and metric made visible
            if(binding?.rbMale?.isChecked == true) {
                binding?.llMetricBodyFatInput?.visibility = View.VISIBLE
                binding?.llImperialBodyFatInput?.visibility = View.INVISIBLE

                binding?.textInputLayoutHipMetric?.visibility = View.INVISIBLE

                binding?.llBodyFatValue?.visibility = View.INVISIBLE
            }
            else{
                binding?.llMetricBodyFatInput?.visibility = View.VISIBLE
                binding?.llImperialBodyFatInput?.visibility = View.INVISIBLE

                binding?.textInputLayoutHipMetric?.visibility = View.VISIBLE
                binding?.textInputLayoutHipImperial?.visibility = View.INVISIBLE

                binding?.llBodyFatValue?.visibility = View.INVISIBLE
            }
        }

        binding?.rbFemale?.setOnClickListener {
//            the metric/imperial hip measurement input is made visible according to unit selected
            if(binding?.rbMetricUnits?.isChecked == true){
                binding?.textInputLayoutHipMetric?.visibility = View.VISIBLE
                binding?.llBodyFatValue?.visibility = View.INVISIBLE
            }
            else{
                binding?.textInputLayoutHipImperial?.visibility = View.VISIBLE
                binding?.llBodyFatValue?.visibility = View.INVISIBLE
            }
        }

        binding?.rbMale?.setOnClickListener {
//            the metric/imperial hip measurement input is made invisible according to unit selected
            if(binding?.rbMetricUnits?.isChecked == true){
                binding?.textInputLayoutHipMetric?.visibility = View.INVISIBLE
                binding?.llBodyFatValue?.visibility = View.INVISIBLE
            }
            else{
                binding?.textInputLayoutHipImperial?.visibility = View.INVISIBLE
                binding?.llBodyFatValue?.visibility = View.INVISIBLE
            }
        }
    }
}