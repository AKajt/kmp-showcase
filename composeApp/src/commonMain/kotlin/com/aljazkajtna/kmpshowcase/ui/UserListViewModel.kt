package com.aljazkajtna.kmpshowcase.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserListViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(UserListScreenState())
    val uiState: StateFlow<UserListScreenState> = _uiState.asStateFlow()
}
