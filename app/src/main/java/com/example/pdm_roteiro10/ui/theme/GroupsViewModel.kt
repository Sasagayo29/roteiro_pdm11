package com.example.pdm_roteiro10.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pdm_roteiro10.domain.Group
import com.example.pdm_roteiro10.domain.GroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val repository: GroupRepository
) : ViewModel() {

    val groups: StateFlow<List<Group>> = repository.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private var _currentGroup by mutableStateOf<Group?>(null)
    val currentGroup: Group? get() = _currentGroup

    var isLoading by mutableStateOf(false)
        private set

    fun setCurrentGroup(group: Group?) {
        _currentGroup = group
    }

    fun saveGroup(group: Group, onSaved: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            delay(500)
            repository.save(group)
            isLoading = false
            onSaved()
        }
    }
}