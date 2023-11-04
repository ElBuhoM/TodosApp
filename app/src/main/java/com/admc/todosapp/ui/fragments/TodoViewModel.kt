package com.admc.todosapp.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admc.todosapp.data.database.entities.TaskEntity
import com.admc.todosapp.domain.model.Task
import com.admc.todosapp.domain.usecase.DeleteTaskUseCase
import com.admc.todosapp.domain.usecase.EditTaskUseCase
import com.admc.todosapp.domain.usecase.GetTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val _taskList = MutableLiveData<List<Task>>()
    val tasList: LiveData<List<Task>> = _taskList




    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getTaskUseCase()
            _taskList.postValue(result)
        }

    }
    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteTaskUseCase(task)
                val result = getTaskUseCase()
                _taskList.postValue(result)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {

                }
            }
        }
    }


}