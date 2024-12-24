package com.example.ucp2pam.ui.theme.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ucp2pam.ui.theme.Screen.DropdownMenuComponent
import com.example.ucp2pam.ViewModel.MainViewModel
import com.example.ucp2pam.database.Jadwal

@Composable
fun EditJadwalScreen(
    navController: NavController,
    jadwal: Jadwal?,
    viewModel: MainViewModel
)
{
    if (jadwal == null) {
        return
    }

    val dokterList by viewModel.dokterList.observeAsState(emptyList())

