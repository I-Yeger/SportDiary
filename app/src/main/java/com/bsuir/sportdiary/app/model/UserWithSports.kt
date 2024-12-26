package com.bsuir.sportdiary.app.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithSports(
    @Embedded
    val user: User,

    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val sports: List<Sport> // Список спортивных активностей пользователя
)