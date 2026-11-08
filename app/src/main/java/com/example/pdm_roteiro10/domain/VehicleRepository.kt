package com.example.pdm_roteiro10.domain

import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun getAll(): Flow<List<Vehicle>>
    suspend fun save(vehicle: Vehicle)
}