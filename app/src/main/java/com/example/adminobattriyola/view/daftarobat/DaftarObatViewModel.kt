package com.example.adminobattriyola.view.daftarobat

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.models.obat.Obat
import com.example.adminobattriyola.models.obat.ObatResponse
import com.example.adminobattriyola.repositories.ObatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaftarObatViewModel @Inject constructor(private val repo: ObatRepo):ViewModel() {

    private val _uiState = MutableStateFlow<ObatResponse>(ObatResponse())
    val uiState = _uiState.asStateFlow()
//    var selectedList by mutableStateOf(mutableListOf<Int>())

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun getObat(cariObat:String,isLoading:MutableState<Boolean>,event:() -> Unit = {}) =
        viewModelScope.launch {
            try {
                isLoading.value = true
                if (cariObat.isEmpty()) {
                    repo.getObat().let {
                        _uiState.value = it
                        isLoading.value = false
                        event.invoke()
                    }
                } else {
                    repo.cariObat(cariObat).let {
                        _uiState.value = it
                        isLoading.value = false
                        event.invoke()
                    }
                }

            } catch (e:Exception) {
                isLoading.value = false
                Log.e("ERROR GET OBAT, ",e.toString())
                event.invoke()

            }
        }

    fun refresh(cari:String,isLoading: MutableState<Boolean>) {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            getObat(cari,isLoading) {
                viewModelScope.launch {
                    _isRefreshing.emit(false)
                }
            }
        }
    }

    val obatName =
        mutableStateListOf(
            "Hufagrip",
            "Kombratin",
            "Mixagrip",
            "Hufagrip",
            "Kombratin",
            "Mixagrip",
            "Hufagrip",
            "Kombratin",
            "Mixagrip",
        )
    val obatType =
        mutableStateListOf(
            "Sirup",
            "Tablet",
            "Injeksi",
            "Sirup",
            "Tablet",
            "Injeksi",
            "Sirup",
            "Tablet",
            "Injeksi",

            )

    val currentAmount =
        mutableStateListOf(
            1,
            4,
            6,
            1,
            4,
            6,
            1,
            4,
            6,
        )
    val categoryList =
        mutableListOf(
            "Semua",
            "Sirup",
            "Tablet",
            "Injeksi"
        )

    val selectedObatList = mutableStateOf(
        (0..1000).map {
            ListItem(isSelected = false)
        }
    )


    fun getPercentList(
        firstVisibleItem: Int,
        allItem: Int,
        allVisible: Int
    ): Float {
        return (firstVisibleItem / (allItem - allVisible).toFloat()) * 100
    }

    val selected =
        mutableStateOf(false)


    val currentIndex =
        mutableStateOf(0)




}

 data class ListItem(var isSelected:Boolean)
