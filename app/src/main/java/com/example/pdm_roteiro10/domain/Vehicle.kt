package com.example.pdm_roteiro10.domain

data class Vehicle(
    val id: Int = 0,
    val brand: String = "",
    val model: String = "",
    val year: String = ""
) {
    // Regra de negócio isolada no domínio
    fun isValid(): Boolean {
        return brand.isNotBlank() && model.isNotBlank() && year.isNotBlank()
    }
}