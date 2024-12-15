package com.aljazkajtna.kmpshowcase.ui.userposts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljazkajtna.kmpshowcase.domain.UsersRepository
import com.aljazkajtna.kmpshowcase.ui.model.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserPostsViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserPostsScreenState())
    val uiState: StateFlow<UserPostsScreenState> = _uiState.asStateFlow()

    fun loadUserPosts(userId: Int) {
        viewModelScope.launch {
            val posts = usersRepository.userPosts(userId)
            _uiState.update {
                it.copy(
                    posts = posts.map { it.toUi() }
                )
            }
        }
    }
}
