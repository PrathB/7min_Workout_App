package com.test.a7minworkout

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.a7minworkout.databinding.ActivityExerciseBinding
import com.test.a7minworkout.databinding.CustomDialogBackConfirmationBinding
import java.util.Locale

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding : ActivityExerciseBinding? = null

    private var restTimer : CountDownTimer? = null
    private var exerciseTimer : CountDownTimer? = null

    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExercise :Int = -1

    private var tts : TextToSpeech? = null

    private var player : MediaPlayer? = null

    private var exerciseStatusAdapter : ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseList = Constants.getExercise()

        tts = TextToSpeech(this,this)

        binding?.toolbarExercise?.setNavigationOnClickListener {
            customDialogBack()
        }

        setRestView()
        setExerciseStatusRecyclerView()
    }

    @SuppressLint("MissingSuperCall")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        customDialogBack()
    }

    private fun customDialogBack(){
//        this fxn displays custom dialog for confirmation when back button is pressed
        val customDialog = Dialog(this@ExerciseActivity)
        val customDialogBinding = CustomDialogBackConfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(customDialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)

        customDialogBinding.btnBackConfirmationYes.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }
        customDialogBinding.btnBackConfirmationNo.setOnClickListener {
            customDialog.dismiss()
        }

        customDialog.show()
    }

    private fun setExerciseStatusRecyclerView(){
        binding?.rvProgress?.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        exerciseStatusAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvProgress?.adapter = exerciseStatusAdapter
    }

    private fun startRestTimer(){
        restTimer = object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding?.tvTimerRest?.text = (millisUntilFinished/1000).toString()
                binding?.progressBarRest?.progress = (millisUntilFinished/1000).toInt()
            }

            override fun onFinish() {
                currentExercise++
                exerciseList!![currentExercise].setIsSelected(true)
                exerciseStatusAdapter!!.notifyDataSetChanged()

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

        try{
            val start_btn_sound_uri = Uri.parse("android.resource://com.test.a7minworkout/"  + R.raw.press_start)
            player = MediaPlayer.create(applicationContext,start_btn_sound_uri)
            player?.isLooping = false
            player?.start()
        }
        catch (e:Exception){
            e.printStackTrace()
        }

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
                exerciseList!![currentExercise].setIsSelected(false)
                exerciseList!![currentExercise].setIsCompleted(true)
                exerciseStatusAdapter!!.notifyDataSetChanged()

                if(currentExercise < exerciseList!!.size - 1) {
                    setRestView()
                }
                else{
                    finish()
                    val intent = Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
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
        exerciseList!![currentExercise].setIsSelected(true)

        if(exerciseTimer!= null){
            exerciseTimer!!.cancel()
            binding?.tvTimerExercise?.text = (30).toString()
            exerciseTimer = null
        }
        if(restTimer != null){
            restTimer!!.cancel()
            restTimer = null
        }

        speakOut(exerciseList!![currentExercise].getName())

        startExerciseTimer()
    }

    private fun resetExerciseTimer(){
        if(restTimer!= null){
            exerciseTimer!!.cancel()
            binding?.tvTimerRest?.text = (30).toString()
            exerciseTimer = null
        }
    }

    private fun speakOut(text: String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The language specified is not supported")
            }
        }
        else{
            Log.e("TTS","Initialization Failed")
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
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if(player!=null){
            player!!.stop()
        }

        binding = null

    }

}