package com.admc.todosapp.domain.usecase

import com.admc.todosapp.data.TaskRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo: TaskRepository) {
    suspend operator fun invoke(email: String, password: String) =
        repo.getUserFromDataBase(email, password)
}