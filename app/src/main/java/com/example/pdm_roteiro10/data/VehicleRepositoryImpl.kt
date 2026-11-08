package com.example.pdm_roteiro10.data

import com.example.pdm_roteiro10.domain.Vehicle
import com.example.pdm_roteiro10.domain.VehicleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val dao: VehicleDao
) : VehicleRepository {

    override fun getAll(): Flow<List<Vehicle>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun save(vehicle: Vehicle) {
        if (vehicle.isValid()) {
            dao.insert(vehicle.toEntity())
        }
    }
}