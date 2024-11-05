package com.example.securevault.data.dao

import androidx.room.*
import com.example.securevault.data.entity.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM cards")
    fun getAllCards(): Flow<List<CardEntity>>

    @Insert
    suspend fun insert(card: CardEntity)

    @Update
    suspend fun update(card: CardEntity)

    @Delete
    suspend fun delete(card: CardEntity)

}