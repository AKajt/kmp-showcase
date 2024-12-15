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
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserDetailsScreenState>(UserDetailsScreenState.Loading)
    val uiState: StateFlow<UserDetailsScreenState> = _uiState.asStateFlow()

    fun onStart(userId: String?) {
        _uiState.value = UserDetailsScreenState.Loading
        viewModelScope.launch {
            if (userId != null) {
                usersRepository.getUserById(userId)?.let { user ->
                    _uiState.value = UserDetailsScreenState.Ready(
                        id = user.id,
                        firstName = user.firstName,
                        lastName = user.lastName,
                        age = user.age.toString(),
                        gender = user.gender
                    )
                } ?: {
                    _uiState.value = UserDetailsScreenState.Ready()
                }
            } else {
                _uiState.value = UserDetailsScreenState.Ready()
            }
        }
    }

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

    fun onDeleteUser(userId: String ) {
        viewModelScope.launch {
            usersRepository.deleteUser(userId)
        }
    }

    fun onUpdateUserClick(
        userId: String,
        firstName: String,
        lastName: String,
        age: String,
        selectedGender: Gender
    ) {
        viewModelScope.launch {
            val user = UserDomainModel(
                id = userId,
                firstName = firstName,
                lastName = lastName,
                age = age.toLong(),
                gender = selectedGender
            )
            usersRepository.updateUser(user)
        }
    }
}
