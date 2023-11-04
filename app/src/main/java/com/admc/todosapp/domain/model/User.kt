package com.admc.todosapp.domain.model

import com.admc.todosapp.data.database.entities.UserEntity

data class User(val id:Long, val name:String = "", val email:String, val password:String)
fun UserEntity.toDomain() = User(id, name, email,password)