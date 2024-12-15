package com.aljazkajtna.kmpshowcase.ui.postcreate

sealed class PostCreateScreenState {
    data object Idle : PostCreateScreenState()
    data object Failed : PostCreateScreenState()
    data object Success : PostCreateScreenState()
}
