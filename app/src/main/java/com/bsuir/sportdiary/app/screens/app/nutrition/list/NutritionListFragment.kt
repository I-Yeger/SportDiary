package com.bsuir.sportdiary.app.screens.app.nutrition.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsuir.sportdiary.R
import com.bsuir.sportdiary.app.views.NutritionViewModel
import com.bsuir.sportdiary.databinding.FragmentNutritionListBinding



class NutritionListFragment : Fragment() {

    private var _binding: FragmentNutritionListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NutritionViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNutritionListBinding.inflate(inflater)

        // Recyclerview
        val adapter = NutritionListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // NutritionViewModel
        viewModel = ViewModelProvider(this)[NutritionViewModel::class.java]
        viewModel.nutritionList.observe(viewLifecycleOwner, Observer { nutrition ->
            adapter.setData(nutrition) })

        // Установка слушателя на FloatingActionButton
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_nutritionListFragment_to_nutritionAddFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}