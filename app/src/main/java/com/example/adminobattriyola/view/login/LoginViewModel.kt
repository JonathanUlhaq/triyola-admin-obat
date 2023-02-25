package com.example.adminobattriyola.view.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor():ViewModel() {
    val id = mutableStateOf("")
    val password = mutableStateOf("")
}