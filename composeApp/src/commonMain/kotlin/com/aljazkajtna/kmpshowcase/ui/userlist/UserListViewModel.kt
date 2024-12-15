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

    private val _uiState = MutableStateFlow<UserListScreenState>(UserListScreenState.Idle())
    val uiState: StateFlow<UserListScreenState> = _uiState.asStateFlow()

    fun setup() {
        _uiState.update {
            UserListScreenState.Idle(it.localUsers)
        }
    }

    fun loadLocalUsers() {
        viewModelScope.launch {
            val users = usersRepository.users()
                .map { it.toUi() }
            _uiState.update { state ->
                when(state) {
                    is UserListScreenState.Failed -> state.copy(localUsers = users)
                    is UserListScreenState.Idle -> state.copy(localUsers = users)
                    is UserListScreenState.Success -> state.copy(localUsers = users)
                }
            }
        }
    }

    fun onDeleteUser(userId: String) {
        viewModelScope.launch {
            usersRepository.deleteUser(userId)
            _uiState.update { state ->
                when(state) {
                    is UserListScreenState.Failed -> state.copy(
                        localUsers = state.localUsers.filter { user ->
                            user.id != userId
                        }
                    )
                    is UserListScreenState.Idle -> state.copy(
                        localUsers = state.localUsers.filter { user ->
                            user.id != userId
                        }
                    )
                    is UserListScreenState.Success -> state.copy(
                        localUsers = state.localUsers.filter { user ->
                            user.id != userId
                        }
                    )
                }
            }
        }
    }

    fun loadExternalUsers() {
        viewModelScope.launch {
            try {
                val users = usersRepository.externalUsers()
                    .map { it.toUi() }
                _uiState.update {
                    UserListScreenState.Success(
                        localUsers = it.localUsers,
                        externalUsers = users
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    UserListScreenState.Failed(it.localUsers)
                }
            }
        }
    }
}
