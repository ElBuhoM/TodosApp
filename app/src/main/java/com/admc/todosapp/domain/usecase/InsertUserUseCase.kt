package com.admc.todosapp.domain.usecase

import com.admc.todosapp.data.TaskRepository
import com.admc.todosapp.data.database.entities.UserEntity
import com.admc.todosapp.domain.model.User
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repo:TaskRepository) {
    suspend operator fun invoke(user:UserEntity) {
        return repo.insertUserFromDataBase(user)
    }

}