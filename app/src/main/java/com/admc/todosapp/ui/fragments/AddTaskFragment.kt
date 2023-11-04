package com.admc.todosapp.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.admc.todosapp.R
import com.admc.todosapp.databinding.FragmentAddTaskBinding

import com.admc.todosapp.ui.dialog.DatePickerFragment
import com.admc.todosapp.ui.dialog.TimePickerFragment
import com.admc.todosapp.utilis.TaskManager
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var fecha: Date

    private val args:AddTaskFragmentArgs by navArgs()
    private var edit:Boolean = false



    private val addTaskViewModel:AddTaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater,container,false)
        return binding.root


    }
    fun clearFields(){
        binding.etTask.setText("")
        binding.etTask.setText("")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etDate.setOnClickListener {
            showDatePickerDialog()
        }
        edit = args.Edit
        addTaskViewModel.message.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        if (edit){
            binding.etDate.setText(TaskManager.getTaskUser()!!.date)
            binding.etTask.setText(TaskManager.getTaskUser()!!.nameTask)
            binding.btnAddTask.setOnClickListener {
                addTaskViewModel.editTask(binding.etTask.text.toString(), binding.etDate.text.toString()){goToTodoFragment()}
                clearFields()
            }
        }else
            setTask()

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_addTaskFragment_to_todoFragment)
        }

    }

    private fun setTask(){
        binding.btnAddTask.setOnClickListener {
            addTaskViewModel.setTask(binding.etTask.text.toString(), binding.etDate.text.toString()){goToTodoFragment()}
            clearFields()
        }

    }
    private fun goToTodoFragment(){
        findNavController().navigate(R.id.action_addTaskFragment_to_todoFragment)
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(childFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        fecha = calendar.time
        showTimePickerDialog()
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment{hour, minute ->  onTimeSelected(hour, minute)}
        timePicker.show(childFragmentManager, "timePicker")
    }


    fun onTimeSelected(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.time = fecha // Si "fecha" es un objeto de tipo Date

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        fecha = calendar.time // Actualizar la fecha con la nueva hora y minutos

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val formattedDate = dateFormat.format(fecha)

        binding.etDate.setText(formattedDate)
    }


}