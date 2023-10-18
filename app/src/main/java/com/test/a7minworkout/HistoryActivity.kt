package com.test.a7minworkout

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.a7minworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var binding : ActivityHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistory)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarHistory?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val historyDao = (application as HistoryApp).db.historyDao()
        fetchDatesFromDatabase(historyDao)

        binding?.ivDelete?.setOnClickListener {
            alertDialogDeleteAllHistory(historyDao)
        }
    }

    private fun fetchDatesFromDatabase(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect(){
                if(it.isNotEmpty()) {
                    val dates = ArrayList<String>()
                    for(entity in it){
                        dates.add(entity.date)
                    }
                    binding?.ivDelete?.visibility = View.VISIBLE
                    binding?.rvHistoryDates?.visibility = View.VISIBLE
                    binding?.tvNoData?.visibility = View.GONE
                    binding?.rvHistoryDates?.layoutManager = LinearLayoutManager(
                        this@HistoryActivity,LinearLayoutManager.VERTICAL,false)

                    binding?.rvHistoryDates?.adapter = HistoryAdapter(dates)
                }
                else{
                    binding?.ivDelete?.visibility = View.INVISIBLE
                    binding?.rvHistoryDates?.visibility = View.GONE
                    binding?.tvNoData?.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun deleteAllHistory(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect(){
                if(it.isNotEmpty()){
                    historyDao.deleteAll()
                }
            }
        }
    }

    private fun alertDialogDeleteAllHistory(historyDao: HistoryDao){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Workout History")
        builder.setMessage("Are you sure you want to delete all workout history?")
        builder.setPositiveButton("Yes"){dialogInterface, _ ->
            deleteAllHistory(historyDao)
        }
        builder.setNegativeButton("No"){dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}