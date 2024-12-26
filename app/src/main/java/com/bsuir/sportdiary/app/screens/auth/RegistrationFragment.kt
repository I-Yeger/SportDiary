package com.bsuir.sportdiary.app.screens.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bsuir.sportdiary.app.model.User
import com.bsuir.sportdiary.app.utils.observeEvent
import com.bsuir.sportdiary.app.views.RegistrationViewModel
import com.bsuir.sportdiary.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.apply {
            btnSend.setOnClickListener {
                val user = User(
                    username = edLogin.text.toString(),
                    password = edPassword.text.toString(),
                    password2 = edPassword2.text.toString()
                )
                viewModel.registration(user)
                edLogin.setText("")
                edPassword.setText("")
                edPassword2.setText("")
            }
        }
        observeShowAuthMessageEvent()
        return binding.root
    }

    private fun observeShowAuthMessageEvent() = viewModel.message.observeEvent(viewLifecycleOwner) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}