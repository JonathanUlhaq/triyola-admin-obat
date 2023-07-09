package com.example.adminobattriyola.view.riwayatobat

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.models.riwayat.RiwayatResponse
import com.example.adminobattriyola.repositories.RiwayatObatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RiwayatObatViewModel @Inject constructor(private val repo:RiwayatObatRepo):ViewModel() {
    val riwayatCategory = listOf(
        "2023",
        "2022",
        "2021",
        "2020",
        "2019",
        "2018"
    )


    private val _uiState = MutableStateFlow<RiwayatResponse>(RiwayatResponse())
    val uiState = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun getAllData(search:String,event:() -> Unit = {}) {
        viewModelScope.launch {
            try {
                _uiState.value = repo.getRiwayatTahun(search)
                event.invoke()
            } catch (e:Exception) {
                Log.d("Error, ",e.toString())
                event.invoke()
            }
        }
    }

    fun refresh(search:String) {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            getAllData(search) {
                viewModelScope.launch {
                    _isRefreshing.emit(false)
                }
            }
        }
    }
}