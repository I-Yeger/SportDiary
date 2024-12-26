package com.bsuir.sportdiary.app.screens.app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bsuir.sportdiary.app.screens.auth.MainActivity
import com.bsuir.sportdiary.app.views.LogoutViewModel
import com.bsuir.sportdiary.databinding.FragmentLogoutBinding

class LogoutFragment : Fragment() {

    private var _binding: FragmentLogoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LogoutViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLogoutBinding.inflate(inflater)
        viewModel.logout()
        Toast.makeText(activity, "Вы успешно покинули приложение!", Toast.LENGTH_SHORT).show()
        requireActivity().startActivity(Intent(activity, MainActivity::class.java))
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}