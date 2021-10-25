package br.com.lucas.businesscardapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class BusinessCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val phone: String,
    @ColumnInfo
    val email: String,
    @ColumnInfo
    val company: String,
    @ColumnInfo
    val customBackground: String
) : Serializable