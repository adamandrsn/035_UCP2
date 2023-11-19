package com.example.navigationwithdataexe

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigationwithdataexe.data.SumberData
import com.example.ucp2.FormViewModel

enum class PengelolaHalaman {
    Home,
    Formulir,
    Summary,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormulirApp(
    viewModel: FormViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold { inner ->
        val uiState by viewModel.stateUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PengelolaHalaman.Home.name,
            modifier = Modifier.padding(inner)
        ) {
            composable(route = PengelolaHalaman.Home.name) {
                HalamanHome(
                    onNextButtonClicked = {
                        navController.navigate(PengelolaHalaman.Formulir.name)
                    }
                )
            }
            composable(route = PengelolaHalaman.Formulir.name){
                val context = LocalContext.current
                HalamanForm(
                    onConfirmButtonClicked = {viewModel.setData(it)
                                             navController.navigate(PengelolaHalaman.Summary.name)},
                    dosenPembimbing = SumberData.namadosen1.map { id -> context.resources.getString(id)},
                    dosenPembimbing2 = SumberData.namadosen1.map { id -> context.resources.getString(id)},
                    onSelectionChanged = {viewModel.setDosenPem1(it)},
                    onSelectionChanged2 = {viewModel.setDosenPem2(it)},
                    onCancelButtonClicked = { navController.popBackStack(
                        PengelolaHalaman.Home.name,inclusive = false) }
                )
            }
            composable(route = PengelolaHalaman.Summary.name) {
                HalamanSummary(uiState = uiState, onCancelButtonClicked = { navController.popBackStack(
                    PengelolaHalaman.Formulir.name,inclusive = false) } )
            }
            }
        }
    }
