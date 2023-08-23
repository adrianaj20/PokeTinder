package com.najarro.adrian.poketinder.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import com.najarro.adrian.poketinder.data.database.entities.MyPokemonEntity
import com.najarro.adrian.poketinder.databinding.FragmentFavoriteBinding
import com.najarro.adrian.poketinder.databinding.FragmentHomeBinding
import com.najarro.adrian.poketinder.ui.adapter.MyPokemonsAdapter
import com.najarro.adrian.poketinder.ui.viewmodel.FavoriteViewModel

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val listMyPokemon = mutableListOf<MyPokemonEntity>()
    private val adapter by lazy {
        MyPokemonsAdapter(listMyPokemon)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriteViewModel.getMyPokemons(requireContext())


        binding.rvPokemons.adapter = adapter

        favoriteViewModel.myPokemonList.observe(this){
            listMyPokemon.addAll(it)
            adapter.notifyDataSetChanged() /* Al eliminar se guarda sin repeticiones pero a la 2da carga*/
        }

        binding.floatingActionDelete.setOnClickListener{
            favoriteViewModel.deleteAllPokemon(requireContext())
        }

    }
}