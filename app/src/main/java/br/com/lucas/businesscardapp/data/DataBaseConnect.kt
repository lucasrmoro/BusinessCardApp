package br.com.lucas.businesscardapp.data

import android.content.Context
import androidx.room.Room

object DataBaseConnect {
    fun getBusinessCardDao(context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "businesscard-db"
    ).build().businessDao()
}