package com.aljazkajtna.kmpshowcase.ui.usersstats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljazkajtna.kmpshowcase.domain.UsersRepository
import kotlinx.coroutines.async
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
            val deferredAverageAge = async { usersRepository.getAverageAge() }
            val deferredGenderCounts = async { usersRepository.getGenderCounts() }

            val averageAge = deferredAverageAge.await()
            val genderCounts = deferredGenderCounts.await()

            _uiState.value = UsersStatsScreenState.Ready(
                averageAge = averageAge,
                maleCount = genderCounts[0],
                femaleCount = genderCounts[1],
                otherCount = genderCounts[2]
            )
        }
    }
}
