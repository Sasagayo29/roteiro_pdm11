package com.example.pdm_roteiro10.domain

import kotlinx.coroutines.flow.Flow

interface GroupRepository {
    fun getAll(): Flow<List<Group>>
    suspend fun save(group: Group)
}