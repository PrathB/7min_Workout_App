package com.test.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.test.a7minworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding : ActivityExerciseBinding? = null

    private var restTimer : CountDownTimer? = null
    private var exerciseTimer : CountDownTimer? = null

    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExercise :Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseList = Constants.getExercise()

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setRestView()
    }

    private fun startRestTimer(){
        restTimer = object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding?.tvTimerRest?.text = (millisUntilFinished/1000).toString()
                binding?.progressBarRest?.progress = (millisUntilFinished/1000).toInt()
            }

            override fun onFinish() {
                currentExercise++
                if(currentExercise < exerciseList!!.size) {
                    setExerciseView()
                    }
            }
        }.start()
    }

    private fun setRestView(){
//        this fxn will cancel any existing timer before calling startRestTimer()
        binding?.flProgressBarExercise?.visibility = View.GONE
        binding?.tvExerciseName?.visibility = View.GONE
        binding?.ivExercise?.visibility = View.GONE

        binding?.flProgressBarRest?.visibility = View.VISIBLE
        binding?.tvRestTitle?.visibility = View.VISIBLE

        binding?.tvUpcomingExercise?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE

        val exerciseName : String = exerciseList!![currentExercise+1].getName()

        binding?.tvUpcomingExerciseName?.text = exerciseName

        if(restTimer!= null ){
            restTimer!!.cancel()
            binding?.tvTimerRest?.text = (10).toString()
            restTimer = null
        }
        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseTimer = null
        }
        startRestTimer()
    }

    private fun resetRestTimer(){
        if(restTimer!= null){
            restTimer!!.cancel()
            binding?.tvTimerRest?.text = (10).toString()
            restTimer = null
        }
    }

    private fun startExerciseTimer(){
        exerciseTimer = object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding?.tvTimerExercise?.text = (millisUntilFinished/1000).toString()
                binding?.progressBarExercise?.progress = (millisUntilFinished/1000).toInt()
            }

            override fun onFinish() {
                if(currentExercise < exerciseList!!.size - 1) {
                    setRestView()
                }
            }
        }.start()
    }

    private fun setExerciseView(){
//        this fxn will cancel any existing timer before calling startExerciseTimer()

        binding?.tvRestTitle?.visibility = View.GONE
        binding?.flProgressBarRest?.visibility = View.GONE

        binding?.flProgressBarExercise?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.ivExercise?.visibility = View.VISIBLE

        binding?.tvUpcomingExercise?.visibility = View.GONE
        binding?.tvUpcomingExerciseName?.visibility = View.GONE

        binding?.tvExerciseName?.text = exerciseList!![currentExercise].getName()
        binding?.ivExercise?.setImageResource(exerciseList!![currentExercise].getImage())

        if(exerciseTimer!= null){
            exerciseTimer!!.cancel()
            binding?.tvTimerExercise?.text = (30).toString()
            exerciseTimer = null
        }
        if(restTimer != null){
            restTimer!!.cancel()
            restTimer = null
        }
        startExerciseTimer()
    }

    private fun resetExerciseTimer(){
        if(restTimer!= null){
            exerciseTimer!!.cancel()
            binding?.tvTimerRest?.text = (30).toString()
            exerciseTimer = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer != null){
            restTimer!!.cancel()
        }
        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
        }
        binding = null
    }
}