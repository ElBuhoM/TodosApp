package com.admc.todosapp.data

import com.admc.todosapp.data.database.dao.TaskDao
import com.admc.todosapp.data.database.entities.TaskEntity
import com.admc.todosapp.data.database.entities.UserEntity
import com.admc.todosapp.domain.model.Task
import com.admc.todosapp.domain.model.User
import com.admc.todosapp.domain.model.toDomain
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    suspend fun insertUserFromDataBase(user: UserEntity) = taskDao.insertUser(user)


    suspend fun getUserFromDataBase(email: String, password: String): List<User> {
        val response: List<UserEntity> = taskDao.getUser(email, password)
        return response.map { it.toDomain() }
    }

    suspend fun getAllTask(userId: Long): List<Task> {
        val response: List<TaskEntity> = taskDao.getTasksByUserId(userId)
        return response.map { it.toDomain() }
    }

    suspend fun insertTask(task: TaskEntity) = taskDao.insertTask(task)

    suspend fun editTask(task: TaskEntity) = taskDao.updateTask(task)

    suspend fun deleteTask(task: TaskEntity) = taskDao.deleteTask(task)


}