package com.example.ucp2pam.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2pam.entity.Jadwal
import kotlinx.coroutines.flow.Flow

@Dao
interface JadwalDao {

    @Insert
    suspend fun insertJadwal(jadwal: Jadwal)

    //getAllJadwal
    @Query("SELECT * FROM jadwal ORDER BY namaPasien ASC")
    fun getAllJadwal(): Flow<List<Jadwal>>

    //Query getJadwal
    @Query("SELECT * FROM jadwal WHERE idJadwal = :idJadwal")
    fun getJadwal(idJadwal: String): Flow<Jadwal>



}