package com.najarro.adrian.poketinder.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.najarro.adrian.poketinder.databinding.ActivityLoginBinding
import com.najarro.adrian.poketinder.ui.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = LoginViewModel(this)
        loginViewModel.onCreate()

        binding.btnLogin.setOnClickListener {
            startLogin()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        loginViewModel.emptyFieldError.observe(this){
            Toast.makeText(this, "Ingrese datos de usuario", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.fieldsAuthenticateError.observe(this){
            Toast.makeText(this,"Error usuario", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.goSuccessActivity.observe(this){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    fun startLogin(){
        loginViewModel.validateInputs(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )
    }
}