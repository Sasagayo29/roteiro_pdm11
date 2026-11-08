package com.example.pdm_roteiro10.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VehicleEntity::class, GroupEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun groupDao(): GroupDao
}