package com.avantsoft.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avantsoft.datasource.repository.UserRepository
import com.avantsoft.track.track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UsersScreenViewModel @Inject constructor(
    // TODO useCase
    private val userRepository: UserRepository,
) : ViewModel() {

    fun fetchUsers() = viewModelScope.launch {
        userRepository.fetchUsers()?.users?.forEach {
            track(it)
        }
    }
}