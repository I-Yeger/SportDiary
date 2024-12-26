package com.bsuir.sportdiary.app.repository

import androidx.lifecycle.LiveData
import com.bsuir.sportdiary.app.model.Nutrition
import com.bsuir.sportdiary.app.model.UserWithNutrition
import com.bsuir.sportdiary.app.setting.AppSettings
import com.bsuir.sportdiary.data.UserDao

class NutritionRepository(
    private val userDao: UserDao,
    private val appSettings: AppSettings
)
{
    val readAllData: LiveData<List<UserWithNutrition>> =
        userDao.getUserWithNutrition(appSettings.getCurrentId())

    // Добавление спортивной активности
    suspend fun addNutrition(nutrition: Nutrition) {
        userDao.addNutrition(nutrition)
    }

    fun getAppDataId(): Int {
        return appSettings.getCurrentId()
    }

    suspend fun updateNutrition(nutrition: Nutrition){
        userDao.updateNutrition(nutrition)
    }

    suspend fun deleteNutrition(nutrition: Nutrition){
        userDao.deleteNutrition(nutrition)
    }
}