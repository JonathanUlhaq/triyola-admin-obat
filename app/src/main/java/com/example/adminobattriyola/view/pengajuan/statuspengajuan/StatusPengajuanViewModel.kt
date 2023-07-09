package com.example.adminobattriyola.view.pengajuan.statuspengajuan

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.models.status.StatusResponse
import com.example.adminobattriyola.repositories.StatusRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatusPengajuanViewModel @Inject constructor(private val repo:StatusRepo):ViewModel() {
    private val _uiState = MutableStateFlow<StatusResponse>(StatusResponse())
    val uiState = _uiState.asStateFlow()

    fun getStatus(search:String) {
        viewModelScope.launch {
            try {
                _uiState.value = repo.getStatusPengajuan(search)
            } catch (e:Exception) {
                Log.e("ERROR STATUS PENGAJAUN, ",e.toString())
            }
        }
    }

}