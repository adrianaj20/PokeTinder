package com.najarro.adrian.poketinder.data.database

import androidx.lifecycle.MutableLiveData
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.najarro.adrian.poketinder.R

class FirebaseRemoteConfigRepository {
    val instance = FirebaseRemoteConfig.getInstance()

    val getUrlPokemonLiveData: MutableLiveData<String> = MutableLiveData()
    val isUnderMaintenanceLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun init(){
        instance.setDefaultsAsync(R.xml.remote_config_defaults)
        instance.fetchAndActivate().addOnCompleteListener { task->
            if (task.isSuccessful){
                getUrlPokemonLiveData.value = getUrlPokemonLive()
                isUnderMaintenanceLiveData.value = getIsUnderMaintenance()

            }
        }
    }

    private fun getUrlPokemonLive(): String{
        return instance["url_pokemon_live"].asString()
    }

    private fun getIsUnderMaintenance(): Boolean {
        return instance["is_under_maintance"].asBoolean()
    }
}