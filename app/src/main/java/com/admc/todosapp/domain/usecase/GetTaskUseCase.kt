package com.admc.todosapp.domain.usecase

import com.admc.todosapp.data.TaskRepository
import com.admc.todosapp.utilis.UserManager
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(private val repo:TaskRepository) {
    suspend operator fun invoke()= repo.getAllTask(UserManager.getInstanceUser().id)
}