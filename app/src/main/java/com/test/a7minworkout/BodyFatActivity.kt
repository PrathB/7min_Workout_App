package com.test.a7minworkout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.test.a7minworkout.databinding.ActivityBodyFatBinding
import kotlin.math.log10

class BodyFatActivity : AppCompatActivity() {

    private var binding: ActivityBodyFatBinding? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyFatBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.bodyFatActivityActionBar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.bodyFatActivityActionBar?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
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

        binding?.btnCalculateBodyFat?.setOnClickListener {
            var bw :Float? = null
            var bfp : Float? = null
            var fm :Float? = null
            var lm : Float? = null
            var category : String? = null

//            for metric input
            if(binding?.rbMetricUnits?.isChecked == true){

//               for metric + male input
                if(binding?.rbMale?.isChecked == true){

//                    checking if fields are empty
                    if(binding?.bodyFatInputWeightMetric?.text.toString().isEmpty()
                        || binding?.bodyFatInputHeightMetric?.text.toString().isEmpty()
                        || binding?.bodyFatInputNeckMetric?.text.toString().isEmpty()
                        || binding?.bodyFatInputWaistMetric?.text.toString().isEmpty()){

                        Toast.makeText(this, "Please Enter All Fields", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        bw = binding?.bodyFatInputWeightMetric?.text.toString().toFloat()

                        bfp= calculateBFPMaleMetric()

                        category = bfCategoryMen(bfp)

                        fm = bfp/100 * bw

                        lm = bw - String.format("%.1f", fm).toFloat()
                    }
                }
//                for metric + female input
                else{
//                    checking if fields are empty
                    if(binding?.bodyFatInputWeightMetric?.text.toString().isEmpty()
                        || binding?.bodyFatInputHeightMetric?.text.toString().isEmpty()
                        || binding?.bodyFatInputNeckMetric?.text.toString().isEmpty()
                        || binding?.bodyFatInputWaistMetric?.text.toString().isEmpty()
                        || binding?.bodyFatInputHipMetric?.text.toString().isEmpty()){

                        Toast.makeText(this, "Please Enter All Fields", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        bw = binding?.bodyFatInputWeightMetric?.text.toString().toFloat()

                        bfp= calculateBFPFemaleMetric()

                        category = bfCategoryWomen(bfp)

                        fm = bfp/100 * bw

                        lm = bw - String.format("%.1f", fm).toFloat()
                    }
                }
                binding?.tvBfpValue?.text = "BODY FAT PERCENTAGE : $bfp %"
                binding?.tvBfCategory?.text  = "CATEGORY : $category"
                binding?.tvFmValue?.text = "FAT MASS : " + String.format("%.1f", fm) +" kgs"
                binding?.tvLmValue?.text = "LEAN MASS : $lm kgs"
            }


//            for imperial input
            else{
//                for imperial + male input
                if(binding?.rbMale?.isChecked == true){

//                    checking if fields are empty
                    if(binding?.bodyFatInputWeightImperial?.text.toString().isEmpty()
                        || binding?.bodyFatInputHeightFeet?.text.toString().isEmpty()
                        || binding?.bodyFatInputHeightInches?.text.toString().isEmpty()
                        || binding?.bodyFatInputNeckImperial?.text.toString().isEmpty()
                        || binding?.bodyFatInputWaistImperial?.text.toString().isEmpty()){

                        Toast.makeText(this, "Please Enter All Fields", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        bw = binding?.bodyFatInputWeightImperial?.text.toString().toFloat()

                        bfp = calculateBFPMaleImperial()

                        category = bfCategoryMen(bfp)

                        fm = bfp/100 * bw

                        lm = bw - String.format("%.1f", fm).toFloat()
                    }
                }
//                for imperial + female input
                else {
//                    checking if fields are empty
                    if (binding?.bodyFatInputWeightImperial?.text.toString().isEmpty()
                        || binding?.bodyFatInputHeightFeet?.text.toString().isEmpty()
                        || binding?.bodyFatInputHeightInches?.text.toString().isEmpty()
                        || binding?.bodyFatInputNeckImperial?.text.toString().isEmpty()
                        || binding?.bodyFatInputWaistImperial?.text.toString().isEmpty()
                        || binding?.bodyFatInputHipImperial?.text.toString().isEmpty()
                    ) {

                        Toast.makeText(this, "Please Enter All Fields", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        bw = binding?.bodyFatInputWeightImperial?.text.toString().toFloat()

                        bfp = calculateBFPFemaleImperial()

                        category = bfCategoryWomen(bfp)

                        fm = bfp/100 * bw

                        lm = bw - String.format("%.1f", fm).toFloat()
                    }
                }

                binding?.tvBfpValue?.text = "BODY FAT PERCENTAGE : $bfp %"
                binding?.tvBfCategory?.text  = "CATEGORY : $category"
                binding?.tvFmValue?.text = "FAT MASS : " + String.format("%.1f", fm) +" lbs"
                binding?.tvLmValue?.text = "LEAN MASS : $lm lbs"

            }

            if(bw!=null) {
                binding?.llBodyFatValue?.visibility = View.VISIBLE
            }

        }
    }

    private fun calculateBFPMaleMetric(): Float {
        val heightcm = binding?.bodyFatInputHeightMetric?.text.toString().toFloat()
        val waistcm = binding?.bodyFatInputWaistMetric?.text.toString().toFloat()
        val neckcm = binding?.bodyFatInputNeckMetric?.text.toString().toFloat()
        val bfp = (495 / (1.0324 - (0.19077 * log10(waistcm-neckcm)) + (0.15456 * log10(heightcm)))) - 450
        return (String.format("%.1f", bfp)).toFloat()
    }

    private fun calculateBFPFemaleMetric(): Float {
        val heightcm = binding?.bodyFatInputHeightMetric?.text.toString().toFloat()
        val waistcm = binding?.bodyFatInputWaistMetric?.text.toString().toFloat()
        val neckcm = binding?.bodyFatInputNeckMetric?.text.toString().toFloat()
        val hipcm = binding?.bodyFatInputHipMetric?.text.toString().toFloat()
        val bfp = (495 / (1.29579 - (0.35004 * log10(waistcm+hipcm-neckcm)) + (0.22100 * log10(heightcm)))) - 450
        return (String.format("%.1f", bfp)).toFloat()
    }

    private fun calculateBFPMaleImperial(): Float {
        val heightfeet = binding?.bodyFatInputHeightFeet?.text.toString().toFloat()
        val heightinches = binding?.bodyFatInputHeightInches?.text.toString().toFloat()
        val waistinches = binding?.bodyFatInputWaistImperial?.text.toString().toFloat()
        val neckinches = binding?.bodyFatInputNeckImperial?.text.toString().toFloat()
        val bfp = 86.010 * log10(waistinches - neckinches) - 70.041 * log10(heightfeet*12 + heightinches) + 36.76
        return (String.format("%.1f", bfp)).toFloat()
    }

    private fun calculateBFPFemaleImperial(): Float {
        val heightfeet = binding?.bodyFatInputHeightFeet?.text.toString().toFloat()
        val heightinches = binding?.bodyFatInputHeightInches?.text.toString().toFloat()
        val waistinches = binding?.bodyFatInputWaistImperial?.text.toString().toFloat()
        val neckinches = binding?.bodyFatInputNeckImperial?.text.toString().toFloat()
        val hipinches= binding?.bodyFatInputHipImperial?.text.toString().toFloat()
        val bfp = 163.205 * log10(waistinches + hipinches - neckinches) - 97.684 * log10(heightfeet*12 + heightinches) - 78.387
        return (String.format("%.1f", bfp)).toFloat()
    }

//    this fxn will return body fat category for men
    private fun bfCategoryMen(bfp : Float) : String{
        val category : String
        when{
            bfp<=2f -> {
                category = "Less Than Essential Fat"
            }
            bfp <= 5f -> {
                category = "Essential Fat"
            }
            bfp <= 13f -> {
                category = "Athletes"
            }
            bfp <= 17f -> {
                category = "Fitness"
            }
            bfp <= 24f -> {
                category = "Average"
            }
            else -> {
                category = "Obese"
            }
        }
        return category
    }

//    this fxn will return body fat category for women
    private fun bfCategoryWomen(bfp : Float) : String{
        val category : String
        when{
            bfp<=10f -> {
                category = "Less Than Essential Fat"
            }
            bfp <= 13f -> {
                category = "Essential Fat"
            }
            bfp <= 20f -> {
                category = "Athletes"
            }
            bfp <= 24f -> {
                category = "Fitness"
            }
            bfp <= 31f -> {
                category = "Average"
            }
            else -> {
                category = "Obese"
            }
        }
        return category
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}

