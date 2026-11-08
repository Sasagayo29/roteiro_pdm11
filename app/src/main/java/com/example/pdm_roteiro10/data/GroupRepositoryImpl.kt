package com.example.pdm_roteiro10.data

import com.example.pdm_roteiro10.domain.Group
import com.example.pdm_roteiro10.domain.GroupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val dao: GroupDao
) : GroupRepository {

    override fun getAll(): Flow<List<Group>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun save(group: Group) {
        if (group.isValid()) {
            dao.insert(group.toEntity())
        }
    }
}