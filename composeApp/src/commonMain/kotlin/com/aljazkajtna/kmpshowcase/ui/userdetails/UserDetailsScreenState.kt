package com.aljazkajtna.kmpshowcase.ui.userdetails

import com.aljazkajtna.kmpshowcase.domain.model.Gender

data class UserDetailsScreenState(
    val firstName: String = "",
    val lastName: String = "",
    val age: String = "",
    val gender: Gender? = null,
)
