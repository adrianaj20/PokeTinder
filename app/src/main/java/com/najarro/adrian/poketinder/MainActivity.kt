package com.najarro.adrian.poketinder

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import com.najarro.adrian.poketinder.adapter.PokemonAdapter
import com.najarro.adrian.poketinder.data.model.PokemonResponse
import com.najarro.adrian.poketinder.data.network.PokemonApi
import com.najarro.adrian.poketinder.databinding.ActivityMainBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    CardStackListener,
    PokemonAdapter.Callback{

    private val adapter by lazy { PokemonAdapter(listPokemon, this)}
    private val listPokemon:List<PokemonResponse> = emptyList()
    private val manager by lazy { CardStackLayoutManager(this,this) }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        initializeTinderCard()
        getAllPokemons()
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun initializeTinderCard(){
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        binding.rvTinderPokemon.layoutManager = manager
        binding.rvTinderPokemon.adapter = adapter
        binding.rvTinderPokemon.itemAnimator.apply {
            if(this is DefaultItemAnimator){
                supportsChangeAnimations = false
            }
        }
    }

    private fun getAllPokemons(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(PokemonApi::class.java).getPokemons()
            if (call.isSuccessful){
                call.body()?.let {
                    runOnUiThread{
                        adapter.list = it.results
                        adapter.notifyDataSetChanged()

                    }
                }
            }
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

    override fun onClickPokemonInformation(pokemon: PokemonResponse) {

        Toast.makeText(this,"Pokemon: ${pokemon.name}",Toast.LENGTH_SHORT).show()
    }
}
