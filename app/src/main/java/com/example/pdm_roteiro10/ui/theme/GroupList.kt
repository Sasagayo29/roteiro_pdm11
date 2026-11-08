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
import com.example.pdm_roteiro10.domain.Group

@Composable
fun GroupList(
    viewModel: GroupsViewModel,
    onNavigateToForm: () -> Unit
) {
    val groups = viewModel.groups.collectAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.setCurrentGroup(null)
                onNavigateToForm()
            }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Grupo")
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
            items(groups) { group ->
                GroupCardItem(
                    group = group,
                    onClick = {
                        viewModel.setCurrentGroup(group)
                        onNavigateToForm()
                    }
                )
            }
        }
    }
}

@Composable
fun GroupCardItem(group: Group, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = group.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = group.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}