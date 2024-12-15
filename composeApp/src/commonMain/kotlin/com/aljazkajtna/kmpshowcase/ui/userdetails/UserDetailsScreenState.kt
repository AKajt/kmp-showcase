package com.aljazkajtna.kmpshowcase.ui.userdetails

import com.aljazkajtna.kmpshowcase.domain.local.Gender

sealed class UserDetailsScreenState {
    data object Loading : UserDetailsScreenState()
    data class Ready(
        val id: String = "",
        val firstName: String = "",
        val lastName: String = "",
        val age: String = "",
        val gender: Gender = Gender.Male
    ) : UserDetailsScreenState()
}
