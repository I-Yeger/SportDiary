package com.bsuir.sportdiary.app.views

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.bsuir.sportdiary.Singletons
import com.bsuir.sportdiary.app.model.Nutrition
import com.bsuir.sportdiary.app.model.UserWithNutrition
import com.bsuir.sportdiary.app.repository.NutritionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NutritionViewModel(application: Application): AndroidViewModel(application)   {
    val readAllData: LiveData<List<UserWithNutrition>>
    private val repository: NutritionRepository
    init {
        repository =  Singletons.nutritionRepository
        readAllData = repository.readAllData
    }

    fun getAppDataId(): Int {
        return repository.getAppDataId()
    }

    // Создаем MediatorLiveData
    val nutritionList = MediatorLiveData<List<Nutrition>>()

    init {
        // Наблюдаем за userWithSports и преобразуем данные
        nutritionList.addSource(readAllData) { userWithNutritionList ->
            // Извлекаем все спортивные активности из списка UserWithSports
            nutritionList.value = userWithNutritionList.flatMap { it.nutritionList }
        }
    }


    fun addNutrition(nutrition: Nutrition){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNutrition(nutrition)
        }
    }

    fun updateNutrition(nutrition: Nutrition){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNutrition(nutrition)
        }
    }

    fun deleteNutrition(nutrition: Nutrition){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNutrition(nutrition)
        }
    }

}