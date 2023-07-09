package com.example.adminobattriyola.models.login

data class LoginResponse(
    val msg:String? = null,
    val user:LoginUser? = null,
    val access_token:String? = null
)
