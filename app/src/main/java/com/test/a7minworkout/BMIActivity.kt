package com.test.a7minworkout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.test.a7minworkout.databinding.ActivityBmiBinding
import kotlin.math.pow

class BMIActivity : AppCompatActivity() {

    private var binding : ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.bmiActivityActionBar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.bmiActivityActionBar?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding?.rbImperialUnits?.setOnClickListener {
//            the metric input field is made invisible and imperial made visible
            binding?.llMetricBmiInput?.visibility = View.INVISIBLE
            binding?.llImperialBmiInput?.visibility = View.VISIBLE
            binding?.llBmiValue?.visibility = View.INVISIBLE
        }

        binding?.rbMetricUnits?.setOnClickListener {
//            the imperial input field is made invisible and metric made visible
            binding?.llMetricBmiInput?.visibility = View.VISIBLE
            binding?.llImperialBmiInput?.visibility = View.INVISIBLE
            binding?.llBmiValue?.visibility = View.INVISIBLE
        }

        binding?.btnCalculateBmi?.setOnClickListener {
//            if metric unit is selected
            if (binding?.rbMetricUnits?.isChecked == true) {
                if (binding?.bmiInputWeightMetric?.text.toString().isEmpty()
                    || binding?.bmiInputHeightMetric?.text.toString().isEmpty()
                ) {

                    Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
                } else {
                    val bmiValMetric = calculateBMIMetric()
                    binding?.tvBmiValue?.text = bmiValMetric.toString()
                    binding?.tvBmiDescription?.text = descriptionBMI(bmiValMetric)
//                    bmi result is made visible
                    binding?.llBmiValue?.visibility = View.VISIBLE
                }
            }

//          if imperial unit is selected
            else {
                if (binding?.bmiInputWeightImperial?.text.toString().isEmpty()
                    || binding?.bmiInputHeightFeet?.text.toString().isEmpty()
                    || binding?.bmiInputHeightInches?.text.toString().isEmpty()
                ) {
                    Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
                } else {
                    val bmiValImperial = calculateBMIImperial()
                    binding?.tvBmiValue?.text = bmiValImperial.toString()
                    binding?.tvBmiDescription?.text = descriptionBMI(bmiValImperial)
//                   bmi result is made visible
                    binding?.llBmiValue?.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun calculateBMIMetric(): Float {
        val heightMeter = binding?.bmiInputHeightMetric?.text.toString().toFloat() / 100
        val weightKg = binding?.bmiInputWeightMetric?.text.toString().toFloat()
        val bmi1 = weightKg / (heightMeter.pow(2))
        return (String.format("%.2f", bmi1)).toFloat()
    }

    private fun calculateBMIImperial(): Float {
        val heightFeet = binding?.bmiInputHeightFeet?.text.toString().toFloat()
        val heightInches = binding?.bmiInputHeightInches?.text.toString().toFloat()
        val totalHeightInches : Float = (heightFeet*12) + heightInches
        val weightLbs = binding?.bmiInputWeightImperial?.text.toString().toFloat()
        val bmi2 = (weightLbs / (totalHeightInches.pow(2))) * 703
        return (String.format("%.2f", bmi2)).toFloat()
    }

//  returns statements for different bmi ranges
    private fun descriptionBMI(x : Float) : String{
        val bmiDescription : String
        when{
            x<=15f -> {
                bmiDescription = "Very Severely Underweight"
            }
            x <= 16f -> {
                bmiDescription = "Severely Underweight"
            }
            x <= 18.5f -> {
                bmiDescription = "Underweight"
            }
            x <= 25f -> {
                bmiDescription = "Normal"
            }
            x <= 30f -> {
                bmiDescription = "Overweight"
            }
            x <= 35f -> {
                bmiDescription = "Obese Class 1 (Moderately Obese)"
            }
            x <= 40f -> {
                bmiDescription = "Obese Class 2 (Severely Obese)"
            }
            else -> {
                bmiDescription = "Obese Class 3 (Very Severely Obese)"
            }
        }
        return bmiDescription
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}