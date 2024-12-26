package com.bsuir.sportdiary.app.views

import androidx.lifecycle.ViewModel
import com.bsuir.sportdiary.Singletons
import com.bsuir.sportdiary.app.repository.UserRepository

class LogoutViewModel(
    private val userRepository: UserRepository = Singletons.userRepository
): ViewModel() {

    fun logout(){
        userRepository.logout();
    }

}