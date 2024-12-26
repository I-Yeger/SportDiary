package com.bsuir.sportdiary.app.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithNutrition(
    @Embedded
    val user: User,

    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val nutritionList: List<Nutrition> // Список записей о питании пользователя
)