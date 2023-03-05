package com.example.adminobattriyola.view.daftarobat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DaftarObatViewModel @Inject constructor():ViewModel() {

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
            "Sirup",
            "Tablet",
            "Injeksi"
        )

    val selectedObatList = mutableStateOf(
        (0..obatName.size).map {
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


    val searchValue =
        mutableStateOf("")

}

 data class ListItem(var isSelected:Boolean)
