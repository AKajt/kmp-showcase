package com.aljazkajtna.kmpshowcase.ui.postcreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljazkajtna.kmpshowcase.domain.UsersRepository
import com.aljazkajtna.kmpshowcase.domain.external.CreatePostRequestDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostCreateViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PostCreateScreenState>(PostCreateScreenState.Idle)
    val uiState: StateFlow<PostCreateScreenState> = _uiState.asStateFlow()
    private var userId: Int? = null

    fun setup(userId: Int) {
        this.userId = userId
        _uiState.update {
            PostCreateScreenState.Idle
        }
    }

    fun createPost(
        title: String,
        body: String
    ) {
        viewModelScope.launch {
            userId?.let { userId ->
                try {
                    usersRepository.createPost(
                        request = CreatePostRequestDomainModel(
                            userId = userId,
                            title = title,
                            body = body
                        )
                    )
                    _uiState.update {
                        PostCreateScreenState.Success
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        PostCreateScreenState.Failed
                    }
                }
            }
        }
    }
}
