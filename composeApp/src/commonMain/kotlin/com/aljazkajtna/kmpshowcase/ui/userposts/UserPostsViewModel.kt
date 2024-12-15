package com.aljazkajtna.kmpshowcase.ui.userposts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljazkajtna.kmpshowcase.domain.UsersRepository
import com.aljazkajtna.kmpshowcase.ui.model.toUi
import com.aljazkajtna.kmpshowcase.ui.postcreate.PostCreateScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserPostsViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserPostsScreenState>(UserPostsScreenState.Idle)
    val uiState: StateFlow<UserPostsScreenState> = _uiState.asStateFlow()
    private var userId: Int? = null

    fun setup(userId: Int) {
        this.userId = userId
        _uiState.update {
            UserPostsScreenState.Idle
        }
    }

    fun loadUserPosts() {
        viewModelScope.launch {
            userId?.let { userId ->
                try {
                    val posts = usersRepository.userPosts(userId)
                    _uiState.update {
                        UserPostsScreenState.Ready(
                            posts = posts.map { it.toUi() }
                        )
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        UserPostsScreenState.Failed
                    }
                }
            }
        }
    }
}
