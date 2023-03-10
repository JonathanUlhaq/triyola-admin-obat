package com.example.adminobattriyola.view.pengajuan.pengajuanscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.relations.DistributorWithObat
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

    val boolean = mutableStateOf(false)
    val booleanAddForm = mutableStateOf(false)
    val booleanUpdate = mutableStateOf(false)
    val booleanUpdateUnit = mutableStateOf(false)
    val obatType = mutableStateOf("")
    val obatName = mutableStateOf("")
    val obatQuantity = mutableStateOf("")
    val obatCurrentType = mutableStateOf("")
    val unitCurrentType = mutableStateOf("")
    val unitType = mutableStateOf("")
    val obatCurrentQuantity = mutableStateOf("")
    val obatCurrentName = mutableStateOf("")

    private val _uiState = MutableStateFlow<List<DistributorWithObat>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun getAllData() = viewModelScope.launch {
        repo.getAllData().collect { list ->
            _uiState.value = list
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