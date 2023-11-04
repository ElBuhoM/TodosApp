package com.admc.todosapp.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.RecyclerView
import com.admc.todosapp.databinding.ItemTaskBinding
import com.admc.todosapp.domain.model.Task

class TaskListViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Task, onClick: () -> Unit) {
        binding.item.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.tvTask.text = item.nameTask
        binding.tvDate.text = item.date
        binding.cbTaks.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onClick()
            }

        }

    }
}
