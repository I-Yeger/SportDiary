package com.bsuir.sportdiary.app.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "sport",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["user_id"])]
)
data class Sport(
    @PrimaryKey
    val id: Int,
    val date: String,
    val time: String,
    val trainingType: String,
    val usedCalories: Int,
    @ColumnInfo(name = "user_id")
    val userId: Int):Parcelable