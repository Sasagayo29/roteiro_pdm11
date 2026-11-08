package com.example.pdm_roteiro10.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pdm_roteiro10.domain.Group

@Entity(tableName = "groups")
data class GroupEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val description: String = ""
) {
    fun toDomain() = Group(id = id, name = name, description = description)
}

fun Group.toEntity() = GroupEntity(id = id, name = name, description = description)