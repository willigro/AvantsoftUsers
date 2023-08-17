package com.avantsoft.ui.users

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.avantsoft.components.lifecycle.DoOnResume
import com.avantsoft.components.ui.progress.ProgressScreen
import com.avantsoft.datasource.network.data.User

// TODO move to user modules if I create it
@Composable
fun UsersScreenRoot(
    navController: NavController,
    viewModel: UsersScreenViewModel = hiltViewModel(),
) {
    DoOnResume {
        viewModel.fetchUsers()
    }

    val uiState: UserUiState = viewModel.uiState.collectAsState().value

    UsersScreen(
        navController = navController,
        uiState = uiState,
    )
}

@Composable
private fun UsersScreen(
    navController: NavController,
    uiState: UserUiState,
) {
    when (uiState) {
        UserUiState.NoUsers -> {

        }

        UserUiState.Loading -> {
            ProgressScreen(modifier = Modifier.fillMaxSize())
        }

        is UserUiState.Listing -> {
            UsersList(
                users = uiState.users,
            )
        }
    }
}

@Composable
fun UsersList(users: List<User>) {
    LazyColumn {
        items(users) { user ->
            Text(text = user.name)
        }
    }
}
