package com.example.pdm_roteiro10.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pdm_roteiro10.domain.Vehicle

@Composable
fun VehiclesList(
    viewModel: VehiclesViewModel,
    onNavigateToForm: () -> Unit
) {
    val vehicles = viewModel.vehicles.collectAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.setCurrentVehicle(null)
                onNavigateToForm()
            }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Veículo")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(vehicles) { vehicle ->
                VehicleCardItem(
                    vehicle = vehicle,
                    onClick = {
                        viewModel.setCurrentVehicle(vehicle)
                        onNavigateToForm()
                    }
                )
            }
        }
    }
}

@Composable
fun VehicleCardItem(vehicle: Vehicle, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${vehicle.brand} ${vehicle.model}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Ano: ${vehicle.year}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}