package com.example.aplikasiklinik.view.otp

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.repositories.LoginRepo
import com.example.adminobattriyola.util.SharePref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor(val repo: LoginRepo, val pref: SharePref):ViewModel() {

    fun getTelepon():String? =
        pref.getTelepon()

    fun fillOTP(otp:String, error:MutableState<Boolean> = mutableStateOf(false),
                loading:MutableState<Boolean> = mutableStateOf(false),
                event:()-> Unit = {}) =
        viewModelScope.launch {
            loading.value = true
            try {
                repo.fillOtp(otp.trim()).let {
                    Log.d("RESPONSE DARI LOGIN",it.toString())
                    pref.saveToken(it.access_token!!)
                    pref.saveName(it.user?.name!!)
                    loading.value = false
                    event.invoke()
                    error.value= false

                }
            } catch (e:Exception) {
                Log.e("EROR MAS, ",e.toString())
                loading.value = false
                error.value= true
            }
        }

    fun doLogin(telepon:String,
                error:MutableState<Boolean> = mutableStateOf(false),
                loading:MutableState<Boolean> = mutableStateOf(false),
                event:()-> Unit = {}) =
        viewModelScope.launch {
            try {
                repo.loginAdmin(telepon.trim()).let {
                    Log.d("RESPONSE DARI LOGIN",it.toString())
                    loading.value = false
                    pref.saveTelepon(telepon.trim())
                    event.invoke()
                    error.value= false

                }
            } catch (e:Exception) {
                Log.e("EROR MAS, ",e.toString())
                loading.value = false
                error.value= true
            }
        }
}