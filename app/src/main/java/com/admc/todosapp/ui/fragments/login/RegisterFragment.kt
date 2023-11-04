package com.admc.todosapp.ui.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.admc.todosapp.R
import com.admc.todosapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    private val registerViewModel:RegisterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        setUser()
    }
    fun setUser(){
        binding.btnRegister.setOnClickListener {
            registerViewModel.setUser(binding.etEmail.text.toString(), binding.etName.text.toString(),binding.etPasswrod.text.toString())
            registerViewModel.message.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                clearFields()
            })

        }

    }

    private fun clearFields(){
        binding.etEmail.setText("")
        binding.etPasswrod.setText("")
        binding.etName.setText("")
    }


}