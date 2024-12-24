package com.example.ucp2pam.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.database.Dokter
import com.example.ucp2pam.database.Jadwal
import com.example.ucp2pam.database.JadwalDatabase
import kotlinx.coroutines.launch

class MainViewModel (application: Application) : AndroidViewModel(application){
    private val database = JadwalDatabase.getDatabase(application)
    private val dokterDao = database.dokterDao()
    private val jadwalDao = database.jadwalDao()

    val dokterList: LiveData<List<Dokter>> = dokterDao.getAllDokter().asLiveData()
    val jadwalList: LiveData<List<Jadwal>> = jadwalDao.getAllJadwal().asLiveData()
