package com.aljazkajtna.kmpshowcase.ui.usersstats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljazkajtna.kmpshowcase.domain.model.UsersRepository
import com.aljazkajtna.kmpshowcase.ui.model.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersStatsViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UsersStatsScreenState>(UsersStatsScreenState.Loading)
    val uiState: StateFlow<UsersStatsScreenState> = _uiState.asStateFlow()

    fun loadStats() {
        _uiState.value = UsersStatsScreenState.Ready(
            averageAge = 41.5,
            maleCount = 4,
            femaleCount = 6,
            otherCount = 1
        )
    }
}
