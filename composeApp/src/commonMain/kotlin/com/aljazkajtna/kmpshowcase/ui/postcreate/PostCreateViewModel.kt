package com.aljazkajtna.kmpshowcase.ui.postcreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aljazkajtna.kmpshowcase.domain.UsersRepository
import com.aljazkajtna.kmpshowcase.ui.model.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostCreateViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostCreateScreenState())
    val uiState: StateFlow<PostCreateScreenState> = _uiState.asStateFlow()

}
