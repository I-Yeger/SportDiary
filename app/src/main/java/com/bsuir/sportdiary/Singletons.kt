package com.bsuir.sportdiary

import android.content.Context
import com.bsuir.sportdiary.app.repository.SportRepository
import com.bsuir.sportdiary.app.repository.UserRepository
import com.bsuir.sportdiary.app.setting.AppSettings
import com.bsuir.sportdiary.data.UserDatabase
import com.bsuir.recreation_facility.app.model.setting.SharedPreferencesAppSettings
import com.bsuir.sportdiary.app.repository.NutritionRepository

object Singletons {

    private lateinit var appContext: Context

    val appSettings: AppSettings by lazy {
        SharedPreferencesAppSettings(appContext)
    }

    // repository
    val userRepository: UserRepository by lazy {
        UserRepository(
            userDao = UserDatabase.getDatabase(appContext).userDao(),
            appSettings = appSettings
        )
    }

    val sportRepository: SportRepository by lazy {
        SportRepository(
            userDao = UserDatabase.getDatabase(appContext).userDao(),
            appSettings = appSettings
        )
    }

    val nutritionRepository: NutritionRepository by lazy {
        NutritionRepository(
            userDao = UserDatabase.getDatabase(appContext).userDao(),
            appSettings = appSettings
        )
    }

    fun init(appContext: Context) {
        Singletons.appContext = appContext

    }
}