package com.bsuir.sportdiary.app.screens.app.nutrition.list

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bsuir.sportdiary.R
import com.bsuir.sportdiary.app.model.Nutrition
import com.bsuir.sportdiary.app.model.Sport
import com.bsuir.sportdiary.app.screens.app.sport.list.SportListAdapter
import com.bsuir.sportdiary.app.screens.app.sport.list.SportListFragmentDirections
import com.bsuir.sportdiary.databinding.CustomNutritionRowBinding
import com.bsuir.sportdiary.databinding.CustomSportRowBinding

class NutritionListAdapter: RecyclerView.Adapter<NutritionListAdapter.MyViewHolder>() {

    private var nutritionList = emptyList<Nutrition>()

    class MyViewHolder(private val binding: CustomNutritionRowBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(nutrition: Nutrition){
            binding.rowDate.text = nutrition.date  ?: "Unknown date"
            binding.rowTime.text = nutrition.time ?: "Unknown time"
            binding.rowProtein.text = nutrition.proteins.toString() ?: "0"
            binding.rowFat.text = nutrition.fats.toString() ?: "0"
            binding.rowCarb.text = nutrition.carb.toString() ?: "0"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CustomNutritionRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  nutritionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(nutritionList[position])

        val rowLayoutId = holder.itemView.findViewById<ConstraintLayout>(R.id.rowNutritionLayout)
        rowLayoutId.setOnClickListener{
            val action = NutritionListFragmentDirections.
            actionNutritionListFragmentToNutritionUpdateFragment(nutritionList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(nutrition: List<Nutrition>) {
        this.nutritionList = nutrition ?.filterNotNull() ?: emptyList()
        notifyDataSetChanged()
    }
}