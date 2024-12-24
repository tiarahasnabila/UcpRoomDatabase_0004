package com.example.ucp2pam

import android.app.Application
import androidx.room.Room
import com.example.ucp2pam.database.JadwalDatabase

class MyApp : Application() {
    val db by lazy {
        Room.databaseBuilder(applicationContext, JadwalDatabase::class.java, "doctor_form")
            .build()
    }
}