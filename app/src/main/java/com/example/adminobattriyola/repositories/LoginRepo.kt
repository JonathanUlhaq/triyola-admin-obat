package com.example.adminobattriyola.repositories

import com.example.adminobattriyola.models.login.LoginResponse
import com.example.adminobattriyola.netowrk.ObatApi
import javax.inject.Inject

class LoginRepo @Inject constructor(private val api:ObatApi) {
    suspend fun loginAdmin(telepon:String) = api.loginAdmin(telepon)
    suspend fun fillOtp(otp:String):LoginResponse = api.fillOtp(otp)
}