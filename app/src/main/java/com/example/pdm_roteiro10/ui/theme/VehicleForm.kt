package com.example.pdm_roteiro10.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pdm_roteiro10.domain.Vehicle

@Composable
fun VehicleForm(viewModel: VehiclesViewModel, onNavigateBack: () -> Unit) {
    val vehicleToEdit = viewModel.currentVehicle
    var brand by remember { mutableStateOf(vehicleToEdit?.brand ?: "") }
    var model by remember { mutableStateOf(vehicleToEdit?.model ?: "") }
    var year by remember { mutableStateOf(vehicleToEdit?.year ?: "") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (vehicleToEdit == null) "Novo Veículo" else "Editar Veículo",
                style = MaterialTheme.typography.titleLarge
            )
            OutlinedTextField(
                value = brand,
                onValueChange = { brand = it },
                label = { Text("Marca") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = model,
                onValueChange = { model = it },
                label = { Text("Modelo") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = year,
                onValueChange = { year = it },
                label = { Text("Ano") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = { onNavigateBack() },
                    enabled = !viewModel.isLoading
                ) {
                    Text("Cancelar")
                }
                Button(
                    enabled = !viewModel.isLoading,
                    onClick = {
                        val vehicle = Vehicle(
                            id = vehicleToEdit?.id ?: 0,
                            brand = brand,
                            model = model,
                            year = year
                        )
                        viewModel.saveVehicle(vehicle) { onNavigateBack() }
                    }
                ) {
                    Text("Salvar")
                }
            }
        }

        if (viewModel.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}