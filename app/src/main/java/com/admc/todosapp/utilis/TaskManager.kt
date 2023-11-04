package com.admc.todosapp.utilis

import com.admc.todosapp.domain.model.Task

object TaskManager {
    private  var task: Task?=null

    fun getTaskUser(): Task? {
        if (task != null)
            return task!!
      return null
    }
    fun setTask(task: Task) {
        this.task = task
    }
    fun clearTask() {
        task = null
    }
}