package com.example.ucp2pam.repository

import com.example.ucp2pam.entity.Dokter
import kotlinx.coroutines.flow.Flow

interface RepositoryDokter {
    suspend fun insertDokter(dokter: Dokter)

    fun getAllDokter(): Flow<List<Dokter>>

    fun getDokter(nim: String): Flow<Dokter>
}