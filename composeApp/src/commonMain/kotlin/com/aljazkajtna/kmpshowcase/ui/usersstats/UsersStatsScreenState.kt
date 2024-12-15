package com.aljazkajtna.kmpshowcase.ui.usersstats

sealed class UsersStatsScreenState {
    data object Loading : UsersStatsScreenState()
    data class Ready(
        val averageAge: Double = 0.0,
        val maleCount: Int = 0,
        val femaleCount: Int = 0,
        val otherCount: Int = 0,
    ) : UsersStatsScreenState()
}
