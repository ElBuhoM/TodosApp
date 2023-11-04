package com.admc.todosapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.admc.todosapp.R
import com.admc.todosapp.data.database.entities.TaskEntity
import com.admc.todosapp.databinding.FragmentTodoBinding
import com.admc.todosapp.ui.adapter.TaskListAdapter
import com.admc.todosapp.utilis.TaskManager
import com.admc.todosapp.utilis.UserManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoFragment : Fragment() {
    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!
    private val todoViewModel: TodoViewModel by viewModels()
    private lateinit var adapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtName.text = UserManager.getInstanceUser().name
        binding.btnAddTask.setOnClickListener {
            goToAddTask(edit = false)
        }
        inintRecyclrerView()
        binding.btnExit.setOnClickListener {

            showLogoutConfirmationDialog()
        }

    }

    private fun showLogoutConfirmationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.apply {
            setTitle("Cerrar Sesión")
            setMessage("¿Está seguro de que desea cerrar sesión?")
            setPositiveButton("Sí") { _, _ ->
                UserManager.clearUserData()
                findNavController().navigate(R.id.action_todoFragment_to_loginFragment)
            }
            setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun goToAddTask(edit: Boolean) {
        findNavController().navigate(TodoFragmentDirections.actionTodoFragmentToAddTaskFragment(edit))
    }

    private fun inintRecyclrerView() {
        todoViewModel.tasList.observe(viewLifecycleOwner, Observer {
            adapter = TaskListAdapter(it) { isChecket() }
            binding.rvTasks.adapter = adapter
            binding.rvTasks.layoutManager = LinearLayoutManager(requireContext())
        })

    }

    fun isChecket() {
        val selectedTask = adapter.getSelectedTask()
        TaskManager.setTask(selectedTask!!)
        binding.btnEdit.setOnClickListener {
            if (TaskManager.getTaskUser() != null) {
                goToAddTask(edit = true)
            }
        }

        binding.btnDelete.setOnClickListener {
            val task = TaskEntity(
                selectedTask!!.id,
                selectedTask.userId,
                selectedTask.nameTask,
                selectedTask.date
            )
            if (selectedTask != null) {
                todoViewModel.deleteTask(task)
                TaskManager.clearTask()
            }
        }
    }
}


