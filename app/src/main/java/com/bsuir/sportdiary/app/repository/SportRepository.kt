package com.bsuir.sportdiary.app.repository

import androidx.lifecycle.LiveData
import com.bsuir.sportdiary.app.model.Sport
import com.bsuir.sportdiary.app.model.UserWithSports
import com.bsuir.sportdiary.app.setting.AppSettings
import com.bsuir.sportdiary.data.UserDao

class SportRepository(
    private val userDao: UserDao,
    private val appSettings: AppSettings
)
{
    val readAllData: LiveData<List<UserWithSports>> =
        userDao.getUserWithSports(appSettings.getCurrentId())

    // Добавление спортивной активности
    suspend fun addSport(sport: Sport) {
        userDao.addSport(sport)
    }

    fun getAppDataId(): Int {
        return appSettings.getCurrentId()
    }

    suspend fun updateSport(sport: Sport){
        userDao.updateSport(sport)
    }

    suspend fun delteSport(sport:Sport){
        userDao.deleteSport(sport)
    }
}