package com.bsuir.sportdiary.app.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.sportdiary.Singletons
import com.bsuir.sportdiary.app.model.User
import com.bsuir.sportdiary.app.repository.UserRepository
import com.bsuir.sportdiary.app.utils.MutableLiveEvent
import com.bsuir.sportdiary.app.utils.MutableUnitLiveEvent
import com.bsuir.sportdiary.app.utils.publishEvent
import com.bsuir.sportdiary.app.utils.share
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository = Singletons.userRepository
): ViewModel() {

    private val _message = MutableLiveEvent<String>()
    val message = _message.share()

    private val _navigateToTabsEvent = MutableUnitLiveEvent()
    val navigateToTabsEvent = _navigateToTabsEvent.share()

    fun login(user: User) {
        viewModelScope.launch {
            if(inputCheck(user.username, user.password)) {
                val res: Boolean = userRepository.login(user)
                if (res) launchTabsScreen()
                else showAuthToast("Такого пользователя не существует")
            }
            else showAuthToast("Пожалуйста заполните все поля")
        }
    }

    private fun showAuthToast(mes: String) = _message.publishEvent(mes)

    private fun launchTabsScreen() = _navigateToTabsEvent.publishEvent()

    private fun inputCheck(firstName: String, password: String): Boolean{
        return !(firstName == "" || password == "")
    }
}