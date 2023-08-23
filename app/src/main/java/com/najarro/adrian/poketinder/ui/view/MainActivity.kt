package com.najarro.adrian.poketinder.ui.view

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.najarro.adrian.poketinder.R
import com.najarro.adrian.poketinder.ui.adapter.PokemonAdapter
import com.najarro.adrian.poketinder.data.model.PokemonResponse
import com.najarro.adrian.poketinder.databinding.ActivityMainBinding
import com.najarro.adrian.poketinder.ui.viewmodel.MainViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){
    private val viewModel by lazy { MainViewModel()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }
}
