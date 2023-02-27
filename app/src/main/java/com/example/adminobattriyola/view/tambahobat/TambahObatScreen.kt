package com.example.adminobattriyola.view.tambahobat

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.BackIcon
import com.example.adminobattriyola.components.ButtonClickSecond
import com.example.adminobattriyola.components.TextTitle
import com.example.adminobattriyola.view.navigation.AppRoute
import com.example.adminobattriyola.widgets.tambahobat.IconFAQ
import com.example.adminobattriyola.widgets.tambahobat.SaveConfirmDialog
import com.example.adminobattriyola.widgets.tambahobat.TambahObatMain

@Composable
fun TambahObatScreen(
    navController: NavController,
    model: TambahObatViewModel,
) {
    model.getAllData()
    val uiState = model.uiState.collectAsState().value
    val addForm = remember {
        mutableStateOf(false)
    }
    val isError = remember {
        mutableStateOf(false)
    }
    val saveDialogShow = remember {
        mutableStateOf(false)
    }

    SaveConfirmDialog(boolean = saveDialogShow) {
        navController.navigate(AppRoute.DaftarObat.route) {
            popUpTo(0)
        }
        model.deleteAllData()
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = {
            ButtonClickSecond(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onSurface,
                text = stringResource(id = R.string.simpan),
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp, top = 20.dp, bottom = 20.dp)
                    .fillMaxWidth()
            ) {
                saveDialogShow.value = true
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    TextTitle(stringResource(R.string.tambah_obat))
                },
                navigationIcon = {
                    BackIcon(navController)
                },
                backgroundColor = MaterialTheme.colors.onPrimary,
                elevation = 0.dp,
                actions = {
                    val showAdviceDialog = remember {
                        mutableStateOf(false)
                    }
                    IconFAQ(showAdviceDialog)
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it),
            color = Color.Transparent
        ) {
            TambahObatMain(uiState, model, addForm, isError)
        }
    }
}

















