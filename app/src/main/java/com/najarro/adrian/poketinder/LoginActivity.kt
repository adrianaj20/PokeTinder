package com.najarro.adrian.poketinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.najarro.adrian.poketinder.data.model.User
import com.najarro.adrian.poketinder.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
   private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreferences(this)
        }
        binding.btnLogin.setOnClickListener {
            startLogin()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    fun startLogin(){
        var email =binding.edtEmail.text.toString()
        var password = binding.edtPassword.text.toString()
        val user: User? = sharedPreferenceUtil.getUser()

        if (email == user?.email && password == user.password){
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            Toast.makeText(this,"Error usuario", Toast.LENGTH_SHORT).show()
        }
    }
}