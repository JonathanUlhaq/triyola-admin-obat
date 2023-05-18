package com.example.adminobattriyola.view.tambahobat

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.repositories.TambahObatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TambahObatViewModel @Inject constructor(private val repo: TambahObatRepo):ViewModel() {
    private val _uiState = MutableStateFlow<List<TambahObatModel>>(emptyList())
    val uiState = _uiState.asStateFlow()

    val boolean = mutableStateOf(false)
    val booleanAddForm = mutableStateOf(false)
    val booleanUpdate = mutableStateOf(false)
    val booleanUpdateUnit = mutableStateOf(false)
    val obatType = mutableStateOf("")
    val obatName = mutableStateOf("")
    val obatQuantity = mutableStateOf("")
    val obatCurrentType = mutableStateOf("")
    val unitCurrentType = mutableStateOf("")
    val dosisObatCurrent = mutableStateOf("")
    val dosisObat = mutableStateOf("")
    val unitType = mutableStateOf("")
    val obatCurrentQuantity = mutableStateOf("")
    val obatCurrentName = mutableStateOf("")

    fun getAllData() =
        viewModelScope.launch {
            repo.getAllData().distinctUntilChanged().collect {
                list ->
                if (list.isNotEmpty()) {
                    _uiState.value = list
                } else {
                    _uiState.value = emptyList()
                }
            }
        }

    fun getDataById(id:Int) =
        viewModelScope.launch {
            repo.getDataById(id).distinctUntilChanged().collect {
                    list ->
                if (list.isNotEmpty()) {
                    _uiState.value = list
                } else {
                    _uiState.value = emptyList()
                }
            }
        }


    fun insertData(model: TambahObatModel) =
        viewModelScope.launch {
            repo.insertData(model)
        }


    fun deleteDatabyId(model: TambahObatModel) =
        viewModelScope.launch {
            repo.deleteData(model)
        }

    fun updateData(model: TambahObatModel) =
        viewModelScope.launch {
            repo.updateData(model)
        }


    fun deleteAllData() =
        viewModelScope.launch {
            repo.deleteAllData()
        }



}