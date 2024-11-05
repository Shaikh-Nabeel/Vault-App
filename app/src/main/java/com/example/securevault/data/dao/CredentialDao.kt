package com.example.securevault.data.dao

import androidx.room.*
import com.example.securevault.data.entity.CredentialEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CredentialDao {
    @Query("SELECT * FROM credentials")
    fun getAllCredentials(): Flow<List<CredentialEntity>>

    @Insert
    suspend fun insert(credential: CredentialEntity)

    @Update
    suspend fun update(credential: CredentialEntity)

    @Delete
    suspend fun delete(credential: CredentialEntity)
}