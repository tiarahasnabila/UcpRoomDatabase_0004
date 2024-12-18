package com.example.ucp2pam.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dokter")
data class Dokter(
    @PrimaryKey
    val id: String,
    val namaDokter: String,
    val spesialis: String,
    val klinik: String,
    val noHp: String,
    val jamKerja: String,
)
