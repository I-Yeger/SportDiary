package com.bsuir.sportdiary.app.screens.app.sport.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bsuir.sportdiary.R
import com.bsuir.sportdiary.app.model.Sport

import com.bsuir.sportdiary.app.views.SportViewModel
import com.bsuir.sportdiary.databinding.FragmentSportAddBinding



class SportAddFragment : Fragment() {

    private var _binding: FragmentSportAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel:SportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSportAddBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[SportViewModel::class.java]

        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val date = binding.addDate.text.toString()
        val time = binding.addTime.text.toString()
        val trainingType = binding.addTrainingType.text.toString()
        val usedCalories = binding.addUsedCalories.text.toString()

        if (inputCheck(date, time, trainingType, usedCalories)){
            // Create Sport object
            val sport = Sport(0, date, time, trainingType,
                Integer.parseInt(usedCalories), viewModel.getAppDataId())
            // Add data to database
            viewModel.addSport(sport)
            Toast.makeText(requireContext(), "Успешно добавленно", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_sportAddFragment_to_sportListFragment)
        }
        else{
            Toast.makeText(requireContext(), "Пожалуйста заполните поля", Toast.LENGTH_LONG).show()
        }


    }

    private fun inputCheck(date: String, time: String,
                           trainingType: String, usedCalories: String): Boolean{
        return !(date == "" || time == "" || trainingType == "" || usedCalories == "")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}