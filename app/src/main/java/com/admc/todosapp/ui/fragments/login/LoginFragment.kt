package com.admc.todosapp.ui.fragments.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.admc.todosapp.R
import com.admc.todosapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginVIewModel: LoginVIewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToRegister()
        login()
        loginVIewModel.message.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })
    }

    private fun login() {
        binding.btnLogin.setOnClickListener {
            loginVIewModel.getUserLogin(
                binding.etEmail.text.toString(),
                binding.etPasswrod.text.toString()
            ) { goToTodoFragment() }

        }

    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun goToTodoFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_todoFragment2)
    }

    private fun goToRegister() {
        binding.btnGoToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            clearFields()
        }
    }
    private fun clearFields(){
        binding.etEmail.setText("")
        binding.etPasswrod.setText("")
    }

}