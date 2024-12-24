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

    var namaDokter by remember { mutableStateOf(jadwal.namaDokter) }
    var namaPasien by remember { mutableStateOf(jadwal.namaPasien) }
    var noHp by remember { mutableStateOf(jadwal.noHp) }
    var tanggalKonsultasi by remember { mutableStateOf(jadwal.tanggalKonsultasi) }
    var status by remember { mutableStateOf(jadwal.status) }

    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.LightGray)
    )
    {
        Text("Edit Jadwal", style = MaterialTheme.typography.displayMedium)

        // Dropdown for Nama Dokter
        DropdownMenuComponent(
            options = dokterList.map { it.namaDokter },
            selectedOption = namaDokter,
            onOptionSelected = { namaDokter = it }
        )
        OutlinedTextField(
            value = namaPasien,
            onValueChange = { namaPasien = it },
            label = { Text("Nama Pasien") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.DarkGray)  // Dark text color
        )

        OutlinedTextField(
            value = noHp,
            onValueChange = { noHp = it },
            label = { Text("No HP") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.DarkGray)  // Dark text color
        )

        OutlinedTextField(
            value = tanggalKonsultasi,
            onValueChange = { tanggalKonsultasi = it },
            label = { Text("Tanggal Konsultasi") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.DarkGray)  // Dark text color
        )

        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.DarkGray)  // Dark text color
        )

        Button(
            onClick = {
                if (namaDokter.isNotEmpty() && namaPasien.isNotEmpty() && noHp.isNotEmpty() && tanggalKonsultasi.isNotEmpty() && status.isNotEmpty()) {
                    // Show confirmation dialog
                    showDialog = true
                } else {
                    Toast.makeText(navController.context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Save Changes")
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false }, // Dismiss dialog on outside touch
                title = { Text("Konfirmasi") },
                text = { Text("Apakah Anda Yakin Ingin Mengedit Jadwal?") },
                confirmButton = {
                    Button(
                        onClick = {
                            // Confirmed, update the Jadwal
                            viewModel.updateJadwal(
                                Jadwal(
                                    id = jadwal.id,
                                    namaDokter = namaDokter,
                                    namaPasien = namaPasien,
                                    noHp = noHp,
                                    tanggalKonsultasi = tanggalKonsultasi,
                                    status = status
                                )
                            )
                            navController.popBackStack() // Go back to the previous screen
                            showDialog = false // Close the dialog
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
}
