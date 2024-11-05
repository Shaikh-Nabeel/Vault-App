package com.example.securevault.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.securevault.data.dao.CardDao
import com.example.securevault.data.dao.CredentialDao
import com.example.securevault.data.entity.CardEntity
import com.example.securevault.data.entity.CredentialEntity

@Database(entities = [CredentialEntity::class, CardEntity::class], version = 1)
abstract class VaultDatabase : RoomDatabase() {
    abstract fun credentialDao(): CredentialDao
    abstract fun cardDao(): CardDao

    companion object {
        @Volatile
        private var INSTANCE: VaultDatabase? = null

        fun getDatabase(context: Context): VaultDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VaultDatabase::class.java,
                    "vault_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}