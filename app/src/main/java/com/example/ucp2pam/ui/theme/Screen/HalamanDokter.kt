package com.example.ucp2pam.ui.theme.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ucp2pam.ViewModel.MainViewModel
import com.example.ucp2pam.database.Dokter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanDokter(viewModel: MainViewModel, navController: NavController) {
    val dokterList by viewModel.dokterList.observeAsState(emptyList())
    var nama by remember { mutableStateOf("") }
    var spesialis by remember { mutableStateOf("") }
    var klinik by remember { mutableStateOf("") }
    var noHp by remember { mutableStateOf("") }
    var jamKerja by remember { mutableStateOf("") }


    var showDialog by remember { mutableStateOf(false) }
    var doctorAdded by remember { mutableStateOf(false) }

    fun addDokter() {
        if (nama.isNotEmpty() && spesialis.isNotEmpty() && klinik.isNotEmpty() && noHp.isNotEmpty() && jamKerja.isNotEmpty()) {
            val dokter = Dokter(
                namaDokter = nama,
                spesialis = spesialis,
                klinik = klinik,
                noHp = noHp,
                jamKerja = jamKerja
            )
            viewModel.addDokter(dokter)

            doctorAdded = true
            nama = ""
            spesialis = ""
            klinik = ""
            noHp = ""
            jamKerja = ""
        } else {
            Toast.makeText(navController.context, "Please fill all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }
    val spesialisOptions = listOf("penyakit dalam", "bedah", "mata", "gizi klinik")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(horizontal = 70.dp, vertical = 60.dp)
                    ){
                        Text("Tambah Dokter",
                            color = Color.Black
                        )
                    }
                },

                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.LightGray)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                OutlinedTextField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = { Text("Nama Dokter") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Spesialis", style = MaterialTheme.typography.bodyLarge)
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    spesialisOptions.forEach { option ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = spesialis == option,
                                onClick = { spesialis = option }
                            )
                            Text(text = option, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }

                Divider(thickness = 1.dp)

                OutlinedTextField(
                    value = klinik,
                    onValueChange = { klinik = it },
                    label = { Text("Klinik") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = noHp,
                    onValueChange = { noHp = it },
                    label = { Text("No HP") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Jam Kerja", style = MaterialTheme.typography.bodyLarge)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    RadioButton(
                        selected = jamKerja == "Pagi",
                        onClick = { jamKerja = "Pagi" }
                    )
                    Text("Pagi", modifier = Modifier.align(Alignment.CenterVertically))
                    RadioButton(
                        selected = jamKerja == "Malam",
                        onClick = { jamKerja = "Malam" }
                    )
                    Text("Malam", modifier = Modifier.align(Alignment.CenterVertically))
                }

                Button(
                    onClick = { addDokter() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tambah Dokter")
                }

