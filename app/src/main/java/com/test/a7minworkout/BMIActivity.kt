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
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.bmiActivityActionBar?.title = "CALCULATE BMI"

        binding?.bmiActivityActionBar?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnCalculateBmi?.setOnClickListener {
            if(binding?.bmiInputWeightMetric?.text.toString().isEmpty()
                || binding?.bmiInputHeightMetric?.text.toString().isEmpty()){

                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
            else{
                val bmiVal = calculateBMI()
                binding?.tvBmiValue?.text = bmiVal.toString()
                binding?.tvBmiDescription?.text = descriptionBMI(bmiVal)
                binding?.llBmiValue?.visibility = View.VISIBLE
            }
        }
    }

    private fun calculateBMI(): Float {
        val heightMeter = binding?.bmiInputHeightMetric?.text.toString().toFloat() / 100
        val weightKg = binding?.bmiInputWeightMetric?.text.toString().toFloat()
        val bmi = weightKg / (heightMeter.pow(2))
        return (String.format("%.2f", bmi)).toFloat()
    }

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


}