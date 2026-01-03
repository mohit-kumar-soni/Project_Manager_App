package com.example.passwordmanagerapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanagerapp.data.Graph
import com.example.passwordmanagerapp.data.Password
import com.example.passwordmanagerapp.data.PasswordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PasswordViewModel(
    private val passwordRepository: PasswordRepository = Graph.passwordRepository
)
    : ViewModel() {
    var passwordTitleState by mutableStateOf("")
    var passwordEmailState by mutableStateOf("")
    var passwordPasswordState by mutableStateOf("")

    fun onPasswordTitleChanged(newString : String){
        passwordTitleState = newString
    }

    fun onPasswordEmailChanged(newString : String){
        passwordEmailState = newString
    }

    fun onPasswordPasswordChanged(newString : String){
        passwordPasswordState = newString
    }

    lateinit var getAllPassword : Flow<List<Password>>

    init {
        viewModelScope.launch { getAllPassword = passwordRepository.getPassword() }
    }

    fun addAPassword(password: Password){
        viewModelScope.launch(Dispatchers.IO) {
            passwordRepository.addAPassword(password = password)
        }
    }
    fun getPasswordById(id: Long) : Flow<Password>{
        return passwordRepository.getPasswordById(id)
    }

    fun updateAPassword(password: Password){
        viewModelScope.launch(Dispatchers.IO) {
            passwordRepository.updateAPassword(password = password)
        }
    }

    fun deleteAPassword(password: Password){
        viewModelScope.launch(Dispatchers.IO) {
            passwordRepository.deleteAPassword(password = password)
        }
    }
}
