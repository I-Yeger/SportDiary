package com.bsuir.sportdiary.app.screens.app.sport.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bsuir.sportdiary.R
import com.bsuir.sportdiary.app.model.Sport
import com.bsuir.sportdiary.databinding.CustomSportRowBinding

class SportListAdapter: RecyclerView.Adapter<SportListAdapter.MyViewHolder>() {

    private var sportList = emptyList<Sport>()

    class MyViewHolder(private val binding: CustomSportRowBinding):
        RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("SetTextI18n")
            fun bind(sport: Sport){
                binding.rowDate.text = sport.date  ?: "Unknown date"
                binding.rowTime.text = sport.time ?: "Unknown time"
                binding.rowTrainingType.text = sport.trainingType ?: "Unknown type"
                binding.rowCalories.text = sport.usedCalories.toString() ?: "0"
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CustomSportRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  sportList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(sportList[position])

        val rowLayoutId = holder.itemView.findViewById<ConstraintLayout>(R.id.rowSportLayout)
        rowLayoutId.setOnClickListener{
            val action = SportListFragmentDirections.actionSportListFragmentToSportUpdateFragment(sportList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(sport: List<Sport>) {
        this.sportList = sport ?.filterNotNull() ?: emptyList()
        notifyDataSetChanged()
    }
}