package com.example.adminobattriyola.view.pengajuan.riwayat

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.models.pengajuanobat.riwayat.RiwayatPengajuanResponse
import com.example.adminobattriyola.models.riwayat.Riwayat
import com.example.adminobattriyola.repositories.PengajuanObatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RiwayatPengajuanViewModel @Inject constructor(private val repo:PengajuanObatRepo):ViewModel() {
    private val _uiState = MutableStateFlow<RiwayatPengajuanResponse>(RiwayatPengajuanResponse())
    val uiState = _uiState.asStateFlow()

    fun getRiwayatPengajuan(search:String,isLoading:MutableState<Boolean>) =
        viewModelScope.launch {
        isLoading.value = true
            try {
                repo.getRiwayatPengajuanByTahun(search).let {
                    _uiState.value = it
                    isLoading.value = false
                }
            } catch (e:Exception) {
                Log.e("RIWAYAT PENGAJUAN ERROR ",e.toString())
                isLoading.value = false
            }
        }
}