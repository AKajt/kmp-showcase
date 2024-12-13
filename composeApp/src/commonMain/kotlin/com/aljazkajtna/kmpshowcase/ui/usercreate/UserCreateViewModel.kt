package com.aljazkajtna.kmpshowcase.ui.usercreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljazkajtna.kmpshowcase.domain.model.UsersRepository
import com.aljazkajtna.kmpshowcase.ui.model.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserCreateViewModel(
    private val usersRepository: UsersRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(UserCreateScreenState())
    val uiState: StateFlow<UserCreateScreenState> = _uiState.asStateFlow()

    init {
    }

}
