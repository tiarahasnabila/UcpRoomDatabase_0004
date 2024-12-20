package com.example.ucp2pam.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2pam.entity.Dokter
import kotlinx.coroutines.flow.Flow


@Dao
interface DokterDao {

    @Insert
    suspend fun insertDokter(dokter: Dokter)

    //getAllDokter
    @Query("SELECT * FROM dokter ORDER BY namaDokter ASC")

    fun getAllDokter(): Flow<List<Dokter>>

    //getDokter
    @Query("SELECT * FROM dokter WHERE id = :id")
    fun getDokter(id: String) : Flow<Dokter>
}