package com.example.pdm_roteiro10.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pdm_roteiro10.domain.Group

@Composable
fun GroupForm(viewModel: GroupsViewModel, onNavigateBack: () -> Unit) {
    val groupToEdit = viewModel.currentGroup
    var name by remember { mutableStateOf(groupToEdit?.name ?: "") }
    var description by remember { mutableStateOf(groupToEdit?.description ?: "") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (groupToEdit == null) "Novo Grupo" else "Editar Grupo",
                style = MaterialTheme.typography.titleLarge
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome do Grupo") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descrição") },
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
                        val group = Group(
                            id = groupToEdit?.id ?: 0,
                            name = name,
                            description = description
                        )
                        viewModel.saveGroup(group) { onNavigateBack() }
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