package com.test.a7minworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.test.a7minworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnFinish?.setOnClickListener {
            finish()
        }

        val historyDao = (application as HistoryApp).db.historyDao()
        addDateToDatabase(historyDao)
    }

    private fun addDateToDatabase(historyDao: HistoryDao){
        val cal = Calendar.getInstance()
        val date = cal.time
        val sdf = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
        val fdate = sdf.format(date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(fdate))
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}