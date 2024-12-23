package com.example.ucp2pam.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2pam.ViewModel.MainViewModel
import com.example.ucp2pam.database.Dokter
import com.example.ucp2pam.database.Jadwal
import com.example.ucp2pam.ui.theme.Screen.EditJadwalScreen
import com.example.ucp2pam.ui.theme.Screen.HalamanDetailDokter
import com.example.ucp2pam.ui.theme.Screen.HalamanDetailJadwal
import com.example.ucp2pam.ui.theme.Screen.HalamanDokter
import com.example.ucp2pam.ui.theme.Screen.HalamanHome
import com.example.ucp2pam.ui.theme.Screen.HalamanJadwal

@Composable
fun AppNavigation(dokterList: List<Dokter>, jadwalList : List<Jadwal>, viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // HomeScreen menerima navController dan dokterList
            HalamanHome(navController = navController)
        }
        composable("dokter") {
            // DokterScreen menerima navController dan viewModel
            HalamanDokter(navController = navController, viewModel = viewModel)
        }
        composable("jadwal") {
            // JadwalScreen menerima navController dan viewModel
            HalamanJadwal(navController = navController, viewModel = viewModel)
        }
        composable("Detail Dokter"){
            // Pastikan untuk menggunakan nama parameter yang benar: navController
            HalamanDetailDokter(navController = navController, dokterList, viewModel = viewModel)
        }
        composable("Detail Jadwal") {
            // JadwalScreen menerima navController dan viewModel
            HalamanDetailJadwal(navController = navController, jadwalList, viewModel = viewModel)
        }
        composable("editJadwal/{jadwalId}") { backStackEntry ->
            val jadwalId = backStackEntry.arguments?.getString("jadwalId")?.toInt()
            val jadwal = jadwalList.find { it.id == jadwalId }
            EditJadwalScreen(navController = navController, jadwal = jadwal, viewModel = viewModel)
        }
    }

}
