package com.najarro.adrian.poketinder.ui.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.najarro.adrian.poketinder.databinding.ActivitySplashBinding
import com.najarro.adrian.poketinder.ui.viewmodel.SplashViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel.getIsUnderMaintenance().observe(this){ isUnderMaintenance ->
            if(isUnderMaintenance){
                Toast.makeText(this, "Bajo Mantenimiento", Toast.LENGTH_SHORT).show()
            }else{
                showAnimation()
            }
        }
    }

    private fun showAnimation(){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 3000)
    }
}