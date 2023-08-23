package com.najarro.adrian.poketinder.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.najarro.adrian.poketinder.databinding.ActivityRegisterBinding
import com.najarro.adrian.poketinder.ui.viewmodel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {
    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel = RegisterViewModel(this)
        registerViewModel.onCreate()

        binding.btnRegister.setOnClickListener {
            startRegister()
        }
        binding.btnGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        registerViewModel.emptyFieldError.observe(this){
            Toast.makeText(this, "Ingrese los datos correctamente", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.goSuccessActivity.observe(this){
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    fun startRegister(){
        registerViewModel.registerInputs(
            userId="1",
            binding.edtUserName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )
    }
}