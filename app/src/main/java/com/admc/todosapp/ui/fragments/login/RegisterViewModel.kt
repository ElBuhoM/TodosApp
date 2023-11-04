package com.admc.todosapp.ui.fragments.login

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admc.todosapp.data.database.entities.UserEntity
import com.admc.todosapp.domain.model.User
import com.admc.todosapp.domain.usecase.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val insertUserUseCase: InsertUserUseCase) :
    ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message


    fun setUser(email: String, name: String, password: String) {
        if (email.isBlank() || name.isBlank() || password.isBlank()) {
            val errorMessage = "Debe introducir datos en todos los campos."
            _message.postValue(errorMessage)
            return
        }

        val user = UserEntity(
            email = email.trim(),
            name = name.trim(),
            password = password.trim()
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    insertUserUseCase(user)
                    val successMessage = "Usuario almacenado correctamente"
                    _message.postValue(successMessage)
                } catch (e: Exception) {
                    val failureMessage = "Error al insertar el usuario: ${e.message}"
                    _message.postValue(failureMessage)
                }
            }
        }
    }

}