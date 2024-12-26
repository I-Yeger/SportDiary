package com.bsuir.sportdiary.app.screens.app.sport.update

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
import com.bsuir.sportdiary.app.model.Sport
import com.bsuir.sportdiary.app.views.SportViewModel
import com.bsuir.sportdiary.databinding.FragmentSportListBinding
import com.bsuir.sportdiary.databinding.FragmentSportUpdateBinding


class SportUpdateFragment : Fragment() {

    private val args by navArgs<SportUpdateFragmentArgs>()
    private var _binding: FragmentSportUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSportUpdateBinding.inflate(inflater)

        binding.updateDate.setText(args.currentSport.date)
        binding.updateTime.setText(args.currentSport.time)
        binding.updateTrainingType.setText(args.currentSport.trainingType)
        binding.updateUsedCalories.setText(args.currentSport.usedCalories.toString())

        viewModel = ViewModelProvider(this)[SportViewModel::class.java]

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
        val trainingType = binding.updateTrainingType.text.toString()
        val usedCalories = binding.updateUsedCalories.text.toString()

        if (inputCheck(date, time, trainingType, usedCalories)){
            // Create Sport Object
            val updatedSport = Sport(args.currentSport.id, date, time, trainingType,
                Integer.parseInt(usedCalories), viewModel.getAppDataId())
            // Update current sport
            viewModel.updateSport(updatedSport)
            Toast.makeText(requireContext(), "Успешно обновлено!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_sportUpdateFragment_to_sportListFragment)
        }else{
            Toast.makeText(requireContext(), "Пожалуйста, заполните все поля!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(date: String, time: String,
                           trainingType: String, usedCalories: String): Boolean{
        return !(date == "" || time == "" || trainingType == "" || usedCalories == "")
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
            viewModel.deleteSport(args.currentSport)
            Toast.makeText(requireContext(), "Тренировка успешно удалена!",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_sportUpdateFragment_to_sportListFragment)

        }
        builder.setNegativeButton("Нет"){_,_->}
        builder.setTitle("Удаление")
        builder.setMessage("Вы точно хотите удалить тренировку?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}