package com.example.adminobattriyola.view.daftarobat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DaftarObatViewModel @Inject constructor():ViewModel() {
    val obatName =
        mutableListOf(
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
        mutableListOf(
            "Obat Bebas",
            "Obat Keras",
            "Obat Keras",
            "Obat Bebas",
            "Obat Keras",
            "Obat Keras",
            "Obat Bebas",
            "Obat Keras",
            "Obat Keras",

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
            "Obat Bebas",
            "Obat Keras"
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



    val enable =
        mutableStateOf(false)

    val searchValue =
        mutableStateOf("")




}