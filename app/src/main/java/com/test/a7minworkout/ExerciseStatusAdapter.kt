package com.test.a7minworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.test.a7minworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(private val items : ArrayList<ExerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>(){
    inner class ViewHolder(binding: ItemExerciseStatusBinding):
        RecyclerView.ViewHolder(binding.root){
            val tvExercisePos = binding.tvItemStatus
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate
            (LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = items[position]
        holder.tvExercisePos.text = model.getId().toString()

        when {
            model.getIsSelected() ->{
                holder.tvExercisePos.background = ContextCompat.getDrawable(
                    holder.itemView.context,R.drawable.exercise_status_bg_sel)

            }
            model.getIsCompleted() ->{
                holder.tvExercisePos.background = ContextCompat.getDrawable(
                    holder.itemView.context,R.drawable.exercise_status_bg_completed)

                holder.tvExercisePos.setTextColor(Color.parseColor("#F79306"))
            }
            else -> {
                holder.tvExercisePos.background = ContextCompat.getDrawable(
                    holder.itemView.context,R.drawable.exercise_status_bg)
            }
        }
    }
}