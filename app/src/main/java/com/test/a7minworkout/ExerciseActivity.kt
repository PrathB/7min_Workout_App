package com.test.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.test.a7minworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding : ActivityExerciseBinding? = null

    private var restTimer : CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setRestView()
    }

    private fun startTimer(){
        binding?.tvTimer?.text = 30.toString()
        restTimer = object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding?.tvTimer?.text = (millisUntilFinished/1000).toString()
                binding?.progressBar?.progress = (millisUntilFinished/1000).toInt()
            }

            override fun onFinish() {
//                TODO: Jump to exercise screen
            }
        }.start()
    }

    private fun setRestView(){
//        this fxn will cancel any existing timer before calling startTimer()
        if(restTimer!= null){
            restTimer!!.cancel()
            binding?.tvTimer?.text = (100000).toString()
            restTimer = null
        }
        startTimer()
    }

    private fun resetTimer(){
        if(restTimer!= null){
            restTimer!!.cancel()
            binding?.tvTimer?.text = (100000).toString()
            restTimer = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer != null){
            restTimer!!.cancel()
        }
        binding = null
    }
}