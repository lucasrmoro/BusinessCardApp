package br.com.lucas.businesscardapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BusinessCardDao {
    @Query("SELECT * FROM BusinessCard")
    suspend fun getAll(): List<BusinessCard>

    @Insert
    suspend fun insert(businessCard: BusinessCard)
}