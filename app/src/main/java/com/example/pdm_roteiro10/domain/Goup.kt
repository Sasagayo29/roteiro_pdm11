package com.example.pdm_roteiro10.domain

data class Group(
    val id: Int = 0,
    val name: String = "",
    val description: String = ""
) {
    fun isValid(): Boolean {
        return name.isNotBlank() && description.isNotBlank()
    }
}