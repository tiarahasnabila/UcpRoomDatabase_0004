package com.example.ucp2pam.ui.theme.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ucp2pam.ViewModel.MainViewModel
import com.example.ucp2pam.database.Dokter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanDetailDokter(
    navController: NavController,
    dokterList: List<Dokter>,
    viewModel: MainViewModel
) {
    var showDialog by remember { mutableStateOf(false) }
    var dokterToDelete by remember { mutableStateOf<Dokter?>(null) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
        .padding(16.dp)) {

        TopAppBar(
            title = {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(horizontal = 50.dp, vertical = 50.dp)
                ) {
                    Text("Daftar Dokter")
                }
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(dokterList) { dokter ->
                DokterCard(
                    dokter = dokter,
                    navController = navController,
                    onDelete = {
                        // Set the doctor to be deleted
                        dokterToDelete = dokter
                        showDialog = true
                    }
                )
            }
        }

        Spacer(Modifier.height(150.dp))

        IconButton(
            onClick = { navController.navigate("home") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home"
            )
        }
    }
    if (showDialog && dokterToDelete != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Konfirmasi Hapus") },
            text = { Text("Apakah Anda yakin ingin menghapus dokter ${dokterToDelete!!.namaDokter}?") },
            confirmButton = {
                Button(
                    onClick = {
                        dokterToDelete?.let {
                            // Call the delete method from the ViewModel
                            viewModel.deleteDokter(it)
                            showDialog = false
                            navController.popBackStack() // Navigate back after deletion
                        }
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}

@Composable
fun DokterCard(dokter: Dokter, navController: NavController, onDelete: () -> Unit) {
    val spesialisColor = when (dokter.spesialis.lowercase()) {
        "penyakit dalam" -> Color.Red
        "bedah" -> Color.Blue
        "mata" -> Color.Green
        "gizi klinik" -> Color.Cyan
        else -> Color.Gray
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ){
            Text(
                text = dokter.namaDokter,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            Text("Nama: ${dokter.namaDokter}", style = MaterialTheme.typography.bodyMedium)
            Text("Spesialis: ${dokter.spesialis}", style = MaterialTheme.typography.bodySmall,color = spesialisColor)
            Text("Klinik: ${dokter.klinik}", style = MaterialTheme.typography.bodySmall)
            Text("No HP: ${dokter.noHp}", style = MaterialTheme.typography.bodySmall)
            Text("Jam Kerja: ${dokter.jamKerja}", style = MaterialTheme.typography.bodySmall)

            Button(
                onClick = { onDelete() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Hapus Dokter")
            }
        }
    }
}


