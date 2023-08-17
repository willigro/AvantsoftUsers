package com.avantsoft.ui.users

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.avantsoft.components.lifecycle.DoOnResume

// TODO move to user modules if I create it
@Composable
fun UsersScreenRoot(
    navController: NavController,
    viewModel: UsersScreenViewModel = hiltViewModel(),
) {
    DoOnResume {
        viewModel.fetchUsers()
    }

    UsersScreen(
        navController = navController,

    )
}

@Composable
fun UsersScreen(
    navController: NavController,
) {

}