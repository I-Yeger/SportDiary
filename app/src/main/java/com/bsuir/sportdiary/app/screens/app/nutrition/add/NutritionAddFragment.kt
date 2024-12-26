package com.bsuir.sportdiary.app.screens.app.nutrition.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bsuir.sportdiary.R
import com.bsuir.sportdiary.app.model.Nutrition

import com.bsuir.sportdiary.app.views.NutritionViewModel
import com.bsuir.sportdiary.databinding.FragmentNutritionAddBinding

class NutritionAddFragment : Fragment() {

    private var _binding: FragmentNutritionAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NutritionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutritionAddBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NutritionViewModel::class.java]

        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val date = binding.addDate.text.toString()
        val time = binding.addTime.text.toString()
        val protein = binding.addProtein.text.toString()
        val fat = binding.addFat.text.toString()
        val carb = binding.addCarb.text.toString()

        if (inputCheck(date, time, protein, fat, carb)){
            // Create Sport object
            val nutrition = Nutrition(0, date, time, Integer.parseInt(protein),
                Integer.parseInt(fat), Integer.parseInt(carb), viewModel.getAppDataId())
            // Add data to database
            viewModel.addNutrition(nutrition)
            Toast.makeText(requireContext(), "Успешно добавленно", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_nutritionAddFragment_to_nutritionListFragment)
        }
        else{
            Toast.makeText(requireContext(), "Пожалуйста заполните поля", Toast.LENGTH_LONG).show()
        }


    }

    private fun inputCheck(date: String, time: String,
                           protein: String, fat: String, carb: String): Boolean{
        return !(date == "" || time == "" || protein == "" || fat == "" || carb == "")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}