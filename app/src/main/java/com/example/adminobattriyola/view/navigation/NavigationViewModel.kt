package com.example.adminobattriyola.view.navigation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.models.UserLogin
import com.example.adminobattriyola.repositories.StatusLogin
import com.example.adminobattriyola.util.SharePref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(private val repo:StatusLogin, val pref:SharePref):ViewModel() {
    private val _uiState = MutableStateFlow<List<UserLogin>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun getToken():String? =
        pref.getToken()

    fun getAllData() =
        viewModelScope.launch {
            repo.getStatus().collect {
                item ->
                if (item.isNotEmpty()) _uiState.value = item
            }
        }
    init {
        getAllData()
    }
    fun insertData(login:UserLogin) =
        viewModelScope.launch {
            repo.insertStatuss(login)
        }

    fun logoutAccount() =
        viewModelScope.launch {
            repo.logoutAccount()
        }
}