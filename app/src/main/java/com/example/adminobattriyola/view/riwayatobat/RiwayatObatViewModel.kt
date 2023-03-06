package com.example.adminobattriyola.view.riwayatobat

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RiwayatObatViewModel @Inject constructor():ViewModel() {
    val riwayatCategory = listOf(
        "2023",
        "2022",
        "2021",
        "2020",
        "2019",
        "2018"
    )
    val selectedCategory = mutableStateOf(false)
    val currentIndex = mutableStateOf(0)
}