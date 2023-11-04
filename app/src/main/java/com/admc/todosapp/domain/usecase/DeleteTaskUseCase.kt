package com.admc.todosapp.domain.usecase

import com.admc.todosapp.data.TaskRepository
import com.admc.todosapp.data.database.entities.TaskEntity
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val repo: TaskRepository) {
    suspend operator fun invoke(task: TaskEntity) =  repo.deleteTask(task)
}