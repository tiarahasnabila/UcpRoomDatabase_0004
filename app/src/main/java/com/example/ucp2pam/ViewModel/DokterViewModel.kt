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

    fun addDokter(dokter: Dokter) {
        viewModelScope.launch {
            dokterDao.insertDokter(dokter)
        }
    }
    fun deleteDokter(dokter: Dokter) {
        viewModelScope.launch {
            dokterDao.deleteDokter(dokter)
        }
    }
    fun addJadwal(jadwal: Jadwal) {
        viewModelScope.launch {
            jadwalDao.insertJadwal(jadwal)
        }
    }

    fun updateJadwal(updatedJadwal: Jadwal) {
        viewModelScope.launch {
            // Update the Jadwal in the database
            jadwalDao.updateJadwal(updatedJadwal)
            // No need to update LiveData manually, it will automatically reflect changes
        }
    }

    fun deleteJadwal(jadwal: Jadwal) {
        viewModelScope.launch {
            // Delete the Jadwal from the database
            jadwalDao.deleteJadwal(jadwal)
            // No need to update LiveData manually, it will automatically reflect changes
        }
    }
}