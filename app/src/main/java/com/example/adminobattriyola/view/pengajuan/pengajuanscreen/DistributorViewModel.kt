package com.example.adminobattriyola.view.pengajuan.pengajuanscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.repositories.DistributorRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DistributorViewModel @Inject constructor(private val repo:DistributorRepo): ViewModel(){
    private val _uiState = MutableStateFlow<List<Distributor>>(emptyList())
    val uiState = _uiState.asStateFlow()

    val boolean = mutableStateOf(false)
    val booleanAddForm = mutableStateOf(false)
    val booleanUpdate = mutableStateOf(false)
    val pengajuanType = mutableStateOf("")
    val distributorName = mutableStateOf("")
    val distributorAddress = mutableStateOf("")
    val distributorCurrentAddress = mutableStateOf("")
    val distributorCurrentName = mutableStateOf("")
    val currentPengajuanType = mutableStateOf("")
    val currentDosis = mutableStateOf("")

    fun getAllData() = viewModelScope.launch {
        repo.getAllData().collect {
            item ->
            if (item.isEmpty()) {
                _uiState.value = emptyList()
            } else {
                _uiState.value = item
            }
        }
    }

    init {
        getAllData()
    }

    fun insertData(distributor: Distributor) = viewModelScope.launch {
        repo.insertData(distributor)
    }

    fun updateData(distributor: Distributor) = viewModelScope.launch {
        repo.updateData(distributor)
    }
    fun deleteAll(distributor:Distributor) = viewModelScope.launch {
        repo.deleteAllData(distributor)
    }

}