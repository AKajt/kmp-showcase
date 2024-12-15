package com.aljazkajtna.kmpshowcase.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljazkajtna.kmpshowcase.domain.UsersRepository
import com.aljazkajtna.kmpshowcase.ui.model.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserListScreenState())
    val uiState: StateFlow<UserListScreenState> = _uiState.asStateFlow()

    fun loadLocalUsers() {
        viewModelScope.launch {
            val users = usersRepository.users()
                .map { it.toUi() }
            _uiState.update {
                it.copy(localUsers = users)
            }
        }
    }

    fun onDeleteUser(userId: String) {
        viewModelScope.launch {
            usersRepository.deleteUser(userId)
            _uiState.update { state ->
                state.copy(
                    localUsers = state.localUsers.filter { user ->
                        user.id != userId
                    }
                )
            }
        }
    }

    fun loadExternalUsers() {
        viewModelScope.launch {
            val users = usersRepository.externalUsers()
                .map { it.toUi() }
            _uiState.update {
                it.copy(externalUsers = users)
            }
        }
    }
}
