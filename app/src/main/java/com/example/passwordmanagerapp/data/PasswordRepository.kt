package com.example.passwordmanagerapp.data

import kotlinx.coroutines.flow.Flow

class PasswordRepository(private val passwordDao: PasswordDao) {

    suspend fun addAPassword(password: Password){
        passwordDao.addAPassword(password)
    }

    fun getPassword(): Flow<List<Password>> = passwordDao.getAllPassword()

    fun getPasswordById(id : Long): Flow<Password>{
        return passwordDao.getPasswordById(id)
    }

    suspend fun updateAPassword(password: Password){
        passwordDao.updateAPassword(password)
    }

    suspend fun deleteAPassword(password: Password){
        passwordDao.deleteAPassword(password)
    }
}