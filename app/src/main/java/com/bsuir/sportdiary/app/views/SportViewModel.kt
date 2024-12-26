package com.bsuir.sportdiary.app.views


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.bsuir.sportdiary.Singletons
import com.bsuir.sportdiary.app.model.Sport
import com.bsuir.sportdiary.app.model.UserWithSports
import com.bsuir.sportdiary.app.repository.SportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SportViewModel (application: Application): AndroidViewModel(application)  {


    val readAllData: LiveData<List<UserWithSports>>
    private val repository: SportRepository
    init {
        repository =  Singletons.sportRepository
        readAllData = repository.readAllData
    }

    fun getAppDataId(): Int {
        return repository.getAppDataId()
    }

    // Создаем MediatorLiveData
    val sportsList = MediatorLiveData<List<Sport>>()

    init {
        // Наблюдаем за userWithSports и преобразуем данные
        sportsList.addSource(readAllData) { userWithSportsList ->
            // Извлекаем все спортивные активности из списка UserWithSports
            sportsList.value = userWithSportsList.flatMap { it.sports }
        }
    }


    fun addSport(sport: Sport){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSport(sport)
        }
    }

    fun updateSport(sport: Sport){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSport(sport)
        }
    }

    fun deleteSport(sport: Sport){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delteSport(sport)
        }
    }

}