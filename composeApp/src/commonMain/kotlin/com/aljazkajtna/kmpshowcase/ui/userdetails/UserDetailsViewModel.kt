package com.aljazkajtna.kmpshowcase.ui.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljazkajtna.kmpshowcase.core.NativeUtils
import com.aljazkajtna.kmpshowcase.domain.model.Gender
import com.aljazkajtna.kmpshowcase.domain.model.UserDomainModel
import com.aljazkajtna.kmpshowcase.domain.model.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val usersRepository: UsersRepository,
    private val nativeUtils: NativeUtils
): ViewModel() {

    private val _uiState = MutableStateFlow(UserDetailsScreenState())
    val uiState: StateFlow<UserDetailsScreenState> = _uiState.asStateFlow()

    fun createUser(firstName: String, lastName: String, age: Int, gender: Gender) {
        viewModelScope.launch {
            val newUser = UserDomainModel(
                id = nativeUtils.randomUUID(),
                firstName = firstName,
                lastName = lastName,
                gender = gender,
                age = age.toLong()
            )
            usersRepository.insertUser(newUser)
        }
    }

}
