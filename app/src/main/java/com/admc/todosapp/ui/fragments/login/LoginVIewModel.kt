package com.admc.todosapp.ui.fragments.login

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admc.todosapp.domain.model.User
import com.admc.todosapp.domain.usecase.GetUserUseCase
import com.admc.todosapp.utilis.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginVIewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getUserLogin(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            if (email.isEmpty() || password.isEmpty()) {
                val failureMessage = "Campos Vacios"
                _message.postValue(failureMessage)
            } else {
                val getUser = getUserUseCase(email, password)
                if (getUser.isNotEmpty()) {
                    UserManager.setUser(User(getUser[0].id, getUser[0].name, getUser[0].email, getUser[0].password))
                   withContext(Dispatchers.Main){
                       onSuccess()
                   }
                } else {
                    val failureMessage = "Usuario o contraseña incorrectos"
                    _message.postValue(failureMessage)
                }
            }
        }
    }

}