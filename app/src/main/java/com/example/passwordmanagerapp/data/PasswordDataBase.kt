package com.example.passwordmanagerapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Password:: class],
    version = 2,
    exportSchema = false
)
abstract class PasswordDataBase: RoomDatabase() {
    abstract fun passwordDao() : PasswordDao
}