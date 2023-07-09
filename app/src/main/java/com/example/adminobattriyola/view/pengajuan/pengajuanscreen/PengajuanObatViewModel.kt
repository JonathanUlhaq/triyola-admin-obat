package com.example.adminobattriyola.view.pengajuan.pengajuanscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.models.pengajuanobat.DistributorResponse
import com.example.adminobattriyola.relations.DistributorWithObat
import com.example.adminobattriyola.repositories.PengajuanObatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

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
    val dosis = mutableStateOf("")
    val obatCurrentQuantity = mutableStateOf("")
    val obatCurrentName = mutableStateOf("")
    val currentDosis = mutableStateOf("")

    private val _uiState = MutableStateFlow<List<DistributorWithObat>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun addDistributor(
        kategori_obat: String,
        nama_distributor: String,
        alamat: String,
        isError: MutableState<Boolean>,
        event:(DistributorResponse) -> Unit
    ) =
        viewModelScope.launch {
            try {
                repo.pengajuanDistributor(
                    kategori_obat,
                    nama_distributor,
                    alamat
                ).let {
                    event.invoke(it)
                    isError.value = false
                }
            } catch (e: Exception) {
                isError.value = true
                Log.e("ERROR ADD DISTRIBUTOR", e.toString())
            }
        }

    fun addPengajuanObat(
        jenis_obat: String,
        nama_obat: String,
        dosis_obat: String,
        jumlah_obat: Int,
        satuan_obat: String,
        distributor_id: Int,
        isError: MutableState<Boolean>
    ) = viewModelScope.launch {
        try {
            repo.pengajuanObat(
                jenis_obat = jenis_obat,
                nama_obat = nama_obat,
                dosis_obat = dosis_obat,
                jumlah_obat = jumlah_obat,
                satuan_obat = satuan_obat,
                distributor_id = distributor_id
            ).let {
                isError.value = false
            }
        } catch (e: Exception) {
            isError.value = true
            Log.e("ERROR PENGAJUAN OBAT,", e.toString())
        }
    }

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