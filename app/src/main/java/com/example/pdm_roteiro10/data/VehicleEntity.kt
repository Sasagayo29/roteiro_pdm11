package com.example.pdm_roteiro10.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pdm_roteiro10.domain.Vehicle

@Entity(tableName = "vehicles")
data class VehicleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val brand: String = "",
    val model: String = "",
    val year: String = ""
) {
    fun toDomain() = Vehicle(id = id, brand = brand, model = model, year = year)
}

fun Vehicle.toEntity() = VehicleEntity(id = id, brand = brand, model = model, year = year)