package com.example.adminobattriyola.view.tambahobat

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.repositories.TambahObatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTambahObatViewModel @Inject constructor(private val repo: TambahObatRepo):ViewModel() {
    private val _uiState = MutableStateFlow<List<TambahObatModel>>(emptyList())
    val uiState = _uiState.asStateFlow()

    val booleanAddForm = mutableStateOf(false)
    val booleanUpdate = mutableStateOf(false)
    val obatCurrentType = mutableStateOf("")
    val obatCurrentQuantity = mutableStateOf("")
    val obatCurrentName = mutableStateOf("")

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
}