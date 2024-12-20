package com.example.ucp2pam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object{
        @Volatile // memastikan bahwa nilai variabel instance selalu sama di semua
        private var Instance: jadwaldrDatabase?= null

        fun getDatabase(context: Context): jadwaldrDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    jadwaldrDatabase::class.java, //class database
                    "RsDatabase" // Nama database
                )
                    .build().also { Instance = it }
            })
        }
    }
}