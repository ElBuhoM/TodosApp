package com.admc.todosapp.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admc.todosapp.data.database.entities.TaskEntity
import com.admc.todosapp.domain.model.Task
import com.admc.todosapp.domain.usecase.EditTaskUseCase
import com.admc.todosapp.domain.usecase.GetTaskUseCase
import com.admc.todosapp.domain.usecase.InsertTaskUseCase
import com.admc.todosapp.utilis.TaskManager
import com.admc.todosapp.utilis.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class AddTaskViewModel @Inject constructor(private  val insertTaskUseCase: InsertTaskUseCase, private val editTaskUseCase: EditTaskUseCase):ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message



    fun setTask(taskName: String, date: String, onSuccess: () -> Unit) {

        if (taskName.isBlank() || date.isBlank()) {
            val errorMessage = "Debe ingresar valores válidos en los campos de tarea y fecha."
            _message.postValue(errorMessage)
            return
        }

        val task = TaskEntity(
            userId = UserManager.getInstanceUser().id,
            taskName = taskName,
            date = date
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                insertTaskUseCase(task)
                val successMessage = "Tarea almacenada correctamente"
                _message.postValue(successMessage)
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } catch (e: Exception) {
                val failureMessage = "Error al insertar tarea: ${e.message}"
                _message.postValue(failureMessage)
            }
        }
    }

    fun editTask(taskName: String, date: String, onSuccess: () -> Unit) {

        if (taskName.isBlank() || date.isBlank()) {
            val errorMessage = "Debe ingresar valores válidos en los campos de tarea y fecha."
            _message.postValue(errorMessage)
            return
        }
        val task = TaskEntity(
            id = TaskManager.getTaskUser()!!.id,
            userId = TaskManager.getTaskUser()!!.userId,
            taskName = taskName,
            date = date
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                editTaskUseCase(task)
                val successMessage = "La tarea se editó correctamente"
                _message.postValue(successMessage)
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } catch (e: Exception) {
                val failureMessage = "Error al actualizar la tarea: ${e.message}"
                _message.postValue(failureMessage)
            }
        }
    }




}