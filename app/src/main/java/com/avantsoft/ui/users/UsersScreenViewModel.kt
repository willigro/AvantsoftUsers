package com.avantsoft.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avantsoft.datasource.repository.UserRepository
import com.avantsoft.common.track.track
import com.avantsoft.datasource.network.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UsersScreenViewModel @Inject constructor(
    // TODO useCase
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState.Loading)
    val uiState: StateFlow<UserUiState>
        get() = _uiState

    fun fetchUsers() = viewModelScope.launch {
        _uiState.value = UserUiState.Loading

        // TODO add flow
        userRepository.fetchUsers()?.users?.also {
            _uiState.value = UserUiState.Listing(users = it)
        } ?: {
            _uiState.value = UserUiState.NoUsers
        }
    }
}

// TODO think about use a simple uiState with mutableStates
sealed class UserUiState {
    object Loading : UserUiState()
    object NoUsers : UserUiState()
    data class Listing(
        val users: List<User>,
    ) : UserUiState()
}