package br.com.lucas.businesscardapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BusinessCard::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun businessDao(): BusinessCardDao
}