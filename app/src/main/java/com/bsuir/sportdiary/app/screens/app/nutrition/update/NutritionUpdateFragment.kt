package com.bsuir.sportdiary.app.screens.app.nutrition.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bsuir.sportdiary.R
import com.bsuir.sportdiary.app.model.Nutrition
import com.bsuir.sportdiary.app.views.NutritionViewModel
import com.bsuir.sportdiary.databinding.FragmentNutritionUpdateBinding


class NutritionUpdateFragment : Fragment() {

    private val args by navArgs<NutritionUpdateFragmentArgs>()
    private var _binding: FragmentNutritionUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NutritionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNutritionUpdateBinding.inflate(inflater)

        binding.updateDate.setText(args.currentNutrition.date)
        binding.updateTime.setText(args.currentNutrition.time)
        binding.updateProtein.setText(args.currentNutrition.proteins.toString())
        binding.updateFat.setText(args.currentNutrition.fats.toString())
        binding.updateCarb.setText(args.currentNutrition.carb.toString())


        viewModel = ViewModelProvider(this)[NutritionViewModel::class.java]

        binding.updateBtn.setOnClickListener{
            updateItem()
        }
        //Add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem() {
        val date = binding.updateDate.text.toString()
        val time = binding.updateTime.text.toString()
        val protein = binding.updateProtein.text.toString()
        val fat = binding.updateFat.text.toString()
        val carb = binding.updateCarb.text.toString()


        if (inputCheck(date, time, protein, fat, carb)){
            // Create Nutrition Object
            val updatedNutrition = Nutrition(0, date, time, Integer.parseInt(protein),
            Integer.parseInt(fat), Integer.parseInt(carb), viewModel.getAppDataId())
            // Update current nutrition
            viewModel.updateNutrition(updatedNutrition)
            Toast.makeText(requireContext(), "Успешно обновлено!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_nutritionUpdateFragment_to_nutritionListFragment)
        }else{
            Toast.makeText(requireContext(), "Пожалуйста, заполните все поля!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(date: String, time: String,
                           protein: String, fat: String, carb: String): Boolean{
        return !(date == "" || time == "" || protein == "" || fat == "" || carb == "")
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да"){_,_->
            viewModel.deleteNutrition(args.currentNutrition)
            Toast.makeText(requireContext(), "Приём пищи успешно удалён!",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_nutritionUpdateFragment_to_nutritionListFragment)

        }
        builder.setNegativeButton("Нет"){_,_->}
        builder.setTitle("Удаление")
        builder.setMessage("Вы точно хотите удалить приём пищи?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}