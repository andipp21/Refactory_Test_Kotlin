package com.refactory.test_refactory.view.auth.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.refactory.test_refactory.databinding.FragmentLoginBinding
import com.refactory.test_refactory.view.auth.AuthPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginFragment(val presenter: AuthPresenter) : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            val eLog = binding.etEmail.text.toString()
            val pLog = binding.etPassword.text.toString()

            GlobalScope.launch {
                presenter.login(eLog, pLog)
            }
        }

        binding.btnRegister.setOnClickListener {
            presenter.goRegisterPage()
        }

        super.onViewCreated(view, savedInstanceState)

    }
}