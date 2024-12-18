package com.example.ucp2pam.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.ucp2pam.entity.Dokter
import kotlinx.coroutines.flow.Flow


@Dao
interface DokterDao {
    @Insert
    suspend fun insertDokter(dokter: Dokter)
    //Query untuk mendapat seluruh data mahasiswa yang diurutkan dari A-Z, A = Ascending
    //Banyak data menggunakan List
    @Query("SELECT * FROM mahasiswa ORDER BY nama ASC")
    //Untuk membungkus data
    fun getAllDokter(): Flow<List<Mahasiswa>>

    //Query untuk mendapat data berdasarkan nim mahasiswa
    @Query("SELECT * FROM mahasiswa WHERE nim = :nim")
    fun getMahasiswa(nim: String) : Flow<Mahasiswa>

    @Delete
    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa)

    @Update
    suspend fun updateMahasiswa(mahasiswa: Mahasiswa)
}