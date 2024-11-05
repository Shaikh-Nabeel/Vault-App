package com.example.securevault.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val number: String,
    val cvv: String,
    val expiryDate: String
)