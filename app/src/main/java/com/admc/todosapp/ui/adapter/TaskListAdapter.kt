package com.admc.todosapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.admc.todosapp.R
import com.admc.todosapp.databinding.ItemTaskBinding
import com.admc.todosapp.domain.model.Task

class TaskListAdapter(private val tasksList: List<Task>, private val onClick: () -> Unit) :
    RecyclerView.Adapter<TaskListViewHolder>() {

    private var selectedTask: Task? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_task, parent, false)
        val binding = ItemTaskBinding.bind(view)
        return TaskListViewHolder(binding)
    }
    override fun getItemCount(): Int = tasksList.size

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val task = tasksList[position]
        holder.bind(task) {
            // Al hacer clic en un elemento de la lista, actualiza la tarea seleccionada
            selectedTask = task
            onClick()
        }
    }
    // Agrega este método para obtener la tarea seleccionada
    fun getSelectedTask(): Task? {
        return selectedTask
    }
}
