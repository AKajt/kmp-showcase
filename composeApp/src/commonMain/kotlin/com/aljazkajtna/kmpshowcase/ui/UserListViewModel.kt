package com.aljazkajtna.kmpshowcase.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljazkajtna.kmpshowcase.domain.model.UsersRepository
import com.aljazkajtna.kmpshowcase.ui.model.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class UserListViewModel(
    private val usersRepository: UsersRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(UserListScreenState())
    val uiState: StateFlow<UserListScreenState> = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            val users = usersRepository.users()
                .map { it.toUi() }
            _uiState.update {
                it.copy(users = users)
            }
        }
    }
}
