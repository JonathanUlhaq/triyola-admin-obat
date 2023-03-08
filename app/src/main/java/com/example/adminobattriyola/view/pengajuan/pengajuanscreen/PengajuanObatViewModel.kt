package com.example.adminobattriyola.view.pengajuan.pengajuanscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.repositories.PengajuanObatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PengajuanObatViewModel @Inject constructor(private val repo: PengajuanObatRepo) :
    ViewModel() {
    private val _uiState = MutableStateFlow<List<PengajuanObat>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun getAllData(obat: String) = viewModelScope.launch {
        repo.getAllData(obat).collect { list ->
            if (list.isNotEmpty()) _uiState.value = list
        }
    }

    fun updateData(obat: PengajuanObat) = viewModelScope.launch {
        repo.updateData(obat)
    }

    fun insertData(obat: PengajuanObat) = viewModelScope.launch {
        repo.insertData(obat)
    }

    fun deleteAll() = viewModelScope.launch {
        repo.deleteAllData()
    }

    fun deleteById(obat: PengajuanObat) = viewModelScope.launch {
        repo.deleteObatbyId(obat)
    }

}