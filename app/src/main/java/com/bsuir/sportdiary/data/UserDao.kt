package com.bsuir.sportdiary.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.bsuir.sportdiary.app.model.Nutrition
import com.bsuir.sportdiary.app.model.Sport
import com.bsuir.sportdiary.app.model.User
import com.bsuir.sportdiary.app.model.UserWithNutrition
import com.bsuir.sportdiary.app.model.UserWithSports

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password LIMIT 1")
    suspend fun checkUserExists(username: String, password: String): User?

    @Query("SELECT count(*) FROM user_table WHERE username = :username LIMIT 1")
    suspend fun validationUser(username: String): Int?

    // Методы для работы с спортивными активностями
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSport(sport: Sport)

    @Update
    suspend fun updateSport(sport: Sport)

    @Delete
    suspend fun deleteSport(sport: Sport)

    @Transaction
    @Query("SELECT * FROM user_table WHERE id = :userId")
    fun getUserWithSports(userId: Int): LiveData<List<UserWithSports>>

    // Методы для работы с питанием
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNutrition(nutrition: Nutrition)

    @Update
    suspend fun updateNutrition(nutrition: Nutrition)

    @Delete
    suspend fun deleteNutrition(nutrition: Nutrition)

    @Transaction
    @Query("SELECT * FROM user_table WHERE id = :userId")
    fun getUserWithNutrition(userId: Int): LiveData<List<UserWithNutrition>>
}