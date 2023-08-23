package com.najarro.adrian.poketinder.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.najarro.adrian.poketinder.data.model.User
import com.najarro.adrian.poketinder.util.SharedPreferenceUtil

class LoginViewModel(
    private val context: Context
): ViewModel() {
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    val emptyFieldError = MutableLiveData<Boolean>()
    val fieldsAuthenticateError = MutableLiveData<Boolean>()
    val goSuccessActivity = MutableLiveData<Boolean>()

    fun onCreate(){
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreferences(context)
        }
    }
    fun validateInputs(
        email: String,
        password: String,
    ){
        if (email.isEmpty() && password.isEmpty()){
            emptyFieldError.postValue(true)
        }
        val user: User? = sharedPreferenceUtil.getUser()
        if (email == user?.email && password == user?.password){
            goSuccessActivity.postValue(true)
        } else {
            fieldsAuthenticateError.postValue(true)
        }
    }
}