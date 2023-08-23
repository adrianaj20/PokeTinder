package com.najarro.adrian.poketinder.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.najarro.adrian.poketinder.data.database.FirebaseRemoteConfigRepository

class SplashViewModel: ViewModel() {
    private var firebaseRemoteConfigRepository = FirebaseRemoteConfigRepository()

    init{
        firebaseRemoteConfigRepository.init()
    }

    fun getIsUnderMaintenance(): MutableLiveData<Boolean>{
        return firebaseRemoteConfigRepository.isUnderMaintenanceLiveData
    }
}