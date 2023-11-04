package com.admc.todosapp.utilis

import com.admc.todosapp.domain.model.User

object UserManager {
    private  var user: User? =null

    fun getInstanceUser():User{
        if (user != null)
            return user!!
        else
            throw Exception("El usuario no ha sido asignado")
    }
    fun setUser(user: User) {
        this.user = user
    }
    fun clearUserData() {
        user = null
    }

}