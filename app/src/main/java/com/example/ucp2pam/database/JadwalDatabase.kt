package com.example.ucp2pam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dokter::class, Jadwal::class], version = 1)
abstract class JadwalDatabase : RoomDatabase() {
    abstract fun dokterDao(): DokterDao
    abstract fun jadwalDao(): JadwalDao

    companion object {
        @Volatile
        private var INSTANCE: JadwalDatabase? = null

        fun getDatabase(context: Context): JadwalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JadwalDatabase::class.java,
                    "dokter_jadwal_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
