package com.example.ucp2pam.ui.theme.Screen

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ucp2pam.ViewModel.MainViewModel
import com.example.ucp2pam.database.Jadwal
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanJadwal(
    viewModel: MainViewModel,
    navController: NavController)
{
    val dokterList by viewModel.dokterList.observeAsState(emptyList())
    val jadwalList by viewModel.jadwalList.observeAsState(emptyList())

    var namaDokter by remember { mutableStateOf("") }
    var namaPasien by remember { mutableStateOf("") }
    var noHp by remember { mutableStateOf("") }
    var tanggalKonsultasi by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        navController.context,
        { _, year, monthOfYear, dayOfMonth ->
            val month = monthOfYear + 1  // Month starts from 0
            tanggalKonsultasi = "$dayOfMonth/$month/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.LightGray)
    ) {
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(horizontal = 50.dp, vertical = 50.dp)
                ) {
                    Text("Tambah Jadwal")
                }
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        DropdownMenuComponent(
            options = dokterList.map { it.namaDokter },
            selectedOption = namaDokter,
            onOptionSelected = { namaDokter = it }
        )

        OutlinedTextField(
            value = namaPasien,
            onValueChange = { namaPasien = it },
            label = { Text("Nama Pasien") },
            modifier = Modifier
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = noHp,
            onValueChange = { noHp = it },
            label = { Text("No HP") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        OutlinedTextField(
            value = tanggalKonsultasi,
            onValueChange = { },
            label = { Text("Tanggal Konsultasi") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { datePickerDialog.show() }) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Select Date")
                }
            }
        )

        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))


        Button(
            onClick = {
                if (namaDokter.isNotEmpty() && namaPasien.isNotEmpty() && noHp.isNotEmpty() && tanggalKonsultasi.isNotEmpty() && status.isNotEmpty()) {
                    showDialog = true
                } else {
                    Toast.makeText(navController.context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tambah Jadwal")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Konfirmasi") },
            text = { Text("Apakah Anda yakin ingin menambahkan jadwal ini?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.addJadwal(
                            Jadwal(
                                namaDokter = namaDokter,
                                namaPasien = namaPasien,
                                noHp = noHp,
                                tanggalKonsultasi = tanggalKonsultasi,
                                status = status
                            )
                        )
                        namaDokter = ""
                        namaPasien = ""
                        noHp = ""
                        tanggalKonsultasi = ""
                        status = ""
                        showDialog = false
                    }
                ) {
                    Text("Ya")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("Tidak")
                }
            }
        )
    }
}

@Composable
fun DropdownMenuComponent(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = { },
            label = { Text("Pilih Dokter") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}



