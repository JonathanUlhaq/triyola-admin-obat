package com.example.adminobattriyola.util

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharePref @Inject constructor(@ApplicationContext context:Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences("PREFS_TOKEN", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString("USER_TOKEN", token)
        editor.apply()
    }

    fun saveName(name:String) {
        val editor = prefs.edit()
        editor.putString("USER_NAME",name)
        editor.apply()
    }

    fun saveTelepon(telepon:String) {
        val editor = prefs.edit()
        editor.putString("USER_TELEPON",telepon)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString("USER_TOKEN", null)
    }
    fun getTelepon():String? {
        return prefs.getString("USER_TELEPON",null)
    }

    fun getName():String? {
        return prefs.getString("USER_NAME",null)
    }
}