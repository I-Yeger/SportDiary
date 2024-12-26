package com.bsuir.sportdiary.app.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.sportdiary.Singletons
import com.bsuir.sportdiary.app.model.User
import com.bsuir.sportdiary.app.repository.UserRepository
import com.bsuir.sportdiary.app.utils.MutableLiveEvent
import com.bsuir.sportdiary.app.utils.publishEvent
import com.bsuir.sportdiary.app.utils.share
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val userRepository: UserRepository = Singletons.userRepository
): ViewModel() {

    private val _message = MutableLiveEvent<String>()
    val message = _message.share()

    fun registration(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            if (inputCheck(user.username, user.password, user.password2)) {
                val res: Boolean = userRepository.validation(user)
                if (!res) {
                    userRepository.registration(user)
                    showAuthToast("Пользователь успешно зарегистрирован")
                } else showAuthToast("Пользователь с таким именем уже зарегистрирован")
            }
            else showAuthToast("Пожалуйста заполните все поля")
        }
    }

    private fun showAuthToast(mes: String) = _message.publishEvent(mes)

    private fun inputCheck(firstName: String, password: String, password2: String): Boolean{
        return !(firstName == "" || password == "" || password2 == "")
    }
}