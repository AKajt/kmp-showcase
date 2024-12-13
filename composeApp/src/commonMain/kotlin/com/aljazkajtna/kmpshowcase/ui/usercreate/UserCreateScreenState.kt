package com.aljazkajtna.kmpshowcase.ui.usercreate

import com.aljazkajtna.kmpshowcase.domain.model.Gender

data class UserCreateScreenState(
    val firstName: String = "",
    val lastName: String = "",
    val age: String = "",
    val gender: Gender? = null,
)
