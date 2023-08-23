package com.najarro.adrian.poketinder.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.annotations.SerializedName
import com.najarro.adrian.poketinder.data.model.User
import com.najarro.adrian.poketinder.ui.view.LoginActivity
import com.najarro.adrian.poketinder.util.SharedPreferenceUtil

class RegisterViewModel(
    private val context: Context
) : ViewModel() {
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    val emptyFieldError = MutableLiveData<Boolean>()
    val goSuccessActivity = MutableLiveData<Boolean>()
    fun onCreate(){
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreferences(context)
        }
    }
    fun registerInputs(
        userId: String,
        userName: String,
        email: String,
        password:String,
        ){
        if (userName.isEmpty() or email.isEmpty() or password.isEmpty()){
            emptyFieldError.postValue(true)
        } else {
            sharedPreferenceUtil.saveUser(User(userId.toString(), userName, email, password))
            goSuccessActivity.postValue(true)
        }
    }
}
