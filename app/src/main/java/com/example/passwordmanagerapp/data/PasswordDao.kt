package com.example.passwordmanagerapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PasswordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAPassword(passWordEntity : Password)

    @Query("Select * from `password_table`")
    abstract fun getAllPassword() : Flow<List<Password>>

    @Update()
    abstract suspend fun updateAPassword(passWordEntity: Password)

    @Delete()
    abstract suspend fun deleteAPassword(passWordEntity: Password)

    @Query("Select * from `password_table` where id =:id")
    abstract fun getPasswordById(id : Long): Flow<Password>
}