package com.bsuir.sportdiary.app.repository

import com.bsuir.sportdiary.app.model.Nutrition
import com.bsuir.sportdiary.app.model.Sport
import com.bsuir.sportdiary.app.model.User
import com.bsuir.sportdiary.app.setting.AppSettings
import com.bsuir.sportdiary.data.UserDao

class UserRepository(
    private val userDao: UserDao,
    private val appSettings: AppSettings
) {


    suspend fun login(user: User): Boolean {
        val user = userDao.checkUserExists(user.username, user.password)
        if(user == null) return false
        else {
            appSettings.setCurrentId(user.id)
        }
        return true
    }

    suspend fun registration(user: User) {
        userDao.addUser(user)
    }

    suspend fun validation(user: User): Boolean{
        return userDao.validationUser(user.username) != 0
    }

    fun logout(){
        appSettings.setCurrentToken("")
        appSettings.setCurrentUsername("")
        appSettings.setCurrentRole("")
    }

}