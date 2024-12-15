package com.aljazkajtna.kmpshowcase.ui.usersstats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljazkajtna.kmpshowcase.domain.model.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersStatsViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UsersStatsScreenState>(UsersStatsScreenState.Loading)
    val uiState: StateFlow<UsersStatsScreenState> = _uiState.asStateFlow()

    fun loadStats() {
        viewModelScope.launch {
            val averageAge = usersRepository.getAverageAge()

            val genderCounts = usersRepository.getGenderCounts()

            _uiState.value = UsersStatsScreenState.Ready(
                averageAge = averageAge,
                maleCount = genderCounts[0],
                femaleCount = genderCounts[1],
                otherCount = genderCounts[2]
            )
        }
    }
}
