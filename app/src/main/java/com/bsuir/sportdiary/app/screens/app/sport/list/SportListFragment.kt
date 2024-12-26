package com.bsuir.sportdiary.app.screens.app.sport.list

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
import com.bsuir.sportdiary.app.views.SportViewModel
import com.bsuir.sportdiary.databinding.FragmentSportListBinding

class SportListFragment : Fragment() {

    private var _binding: FragmentSportListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel:SportViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSportListBinding.inflate(inflater)

        // Recyclerview
        val adapter = SportListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // SportViewModel
        viewModel = ViewModelProvider(this)[SportViewModel::class.java]
        viewModel.sportsList.observe(viewLifecycleOwner, Observer { sport ->
            adapter.setData(sport) })

        // Установка слушателя на FloatingActionButton
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_sportListFragment_to_sportAddFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}