package com.example.ucp2pam.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ucp2pam.dao.DokterDao
import com.example.ucp2pam.dao.JadwalDao
import com.example.ucp2pam.entity.Dokter
import com.example.ucp2pam.entity.Jadwal

@Database(entities = [Dokter::class, Jadwal::class], version = 1, exportSchema = false)
abstract class jadwaldrDatabase : RoomDatabase() {
    //mendefinisikan fungsi mengakses data dokter dan jadwal
    abstract fun dokterDao(): DokterDao
    abstract fun jadwalDao(): JadwalDao
}