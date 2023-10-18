package com.test.a7minworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.test.a7minworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter(private val dates : ArrayList<String>):
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    inner class ViewHolder(binding: ItemHistoryRowBinding):
        RecyclerView.ViewHolder(binding.root){
        val llHistoryRow = binding.llHistoryRow
        val serialNo = binding.tvSerialNo
        val date = binding.date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRowBinding.inflate
            (LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = dates[position]
        holder.serialNo.text = (position + 1).toString()
        holder.date.text = date

        if(position%2 == 0){
            holder.llHistoryRow.setBackgroundColor(ContextCompat.getColor
                (holder.itemView.context,R.color.recycle_view_bg_color))
        }

    }
}