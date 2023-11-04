package com.admc.todosapp.domain.model

import com.admc.todosapp.data.database.entities.TaskEntity

data class Task( val id:Long, val userId:Long, val nameTask:String, val date:String,)
fun TaskEntity.toDomain() = Task( id= id, userId = userId, nameTask = taskName, date = date)
