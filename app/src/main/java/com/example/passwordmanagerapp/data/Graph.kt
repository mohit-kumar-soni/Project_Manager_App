package com.example.passwordmanagerapp.data

import android.content.Context
import androidx.room.Room

object Graph {

    lateinit var dataBase: PasswordDataBase

    val passwordRepository by lazy {
        PasswordRepository(passwordDao = dataBase.passwordDao())
    }
    fun provide(context: Context){
        dataBase = Room.databaseBuilder(context, PasswordDataBase::class.java,"passwordList.db")
            .fallbackToDestructiveMigration(false)
            .build()
    }
}