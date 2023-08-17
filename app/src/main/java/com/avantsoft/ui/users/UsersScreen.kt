package com.avantsoft.ui.users

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.avantsoft.components.lifecycle.DoOnResume
import com.avantsoft.components.theme.AppTheme
import com.avantsoft.components.theme.ListUsersAvantsoftTheme
import com.avantsoft.components.ui.progress.ProgressScreen
import com.avantsoft.components.ui.text.TextBody
import com.avantsoft.components.ui.text.TextSubtitle
import com.avantsoft.components.ui.text.TextTitle
import com.avantsoft.datasource.network.data.User
import com.avantsoft.R
import com.avantsoft.components.theme.Blue30
import com.avantsoft.components.theme.Blue40
import com.avantsoft.components.theme.Green40

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
                usersChuncks = uiState.users,
            )
        }
    }
}

@Composable
private fun UsersList(usersChuncks: List<List<User>>) {
    // TODO move it to AppColors?
    val borderColors = listOf(
        Blue30,
        Blue40,
        Green40,
    )

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(usersChuncks) { usersChunck ->
            LazyRow {
                items(usersChunck) { user ->
                    UserCard(user = user, borderColors = borderColors)
                }
            }
        }
    }
}

// TODO should I draw it with a fixed size (same for all in the row or all card in general)?
@Composable
private fun UserCard(user: User, borderColors: List<Color>) {
    Card(
        modifier = Modifier
            .padding(AppTheme.dimensions.paddingMedium)
            .border(
                width = AppTheme.dimensions.listUserDimens.borderWidth,
                brush = Brush.horizontalGradient(colors = borderColors),
                shape = RoundedCornerShape(5),
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = AppTheme.dimensions.paddingMedium,
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(AppTheme.dimensions.paddingLarge)
                .fillMaxWidth()
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (name, divisor, emailLabel, emailValue, ageLabel, ageValue, idLabel, idValue) = createRefs()

                TextTitle(
                    text = user.name,
                    modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                        .padding(vertical = AppTheme.dimensions.paddingMedium),
                )

                Divider(
                    modifier = Modifier
                        .constrainAs(divisor) {
                            top.linkTo(name.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        },
                    thickness = AppTheme.dimensions.divider,
                    color = MaterialTheme.colorScheme.tertiary,
                )

                TextSubtitle(
                    text = stringResource(id = R.string.user_list_user_card_email_label),
                    modifier = Modifier
                        .constrainAs(emailLabel) {
                            top.linkTo(divisor.bottom)
                            start.linkTo(divisor.start)
                        }
                        .padding(top = AppTheme.dimensions.paddingMedium),
                )

                TextBody(
                    text = user.email,
                    modifier = Modifier.constrainAs(emailValue) {
                        top.linkTo(emailLabel.bottom)
                        start.linkTo(emailLabel.start)
                    },
                )

                TextSubtitle(
                    text = stringResource(id = R.string.user_list_user_card_age_label),
                    modifier = Modifier
                        .constrainAs(ageLabel) {
                            top.linkTo(emailValue.bottom)
                            start.linkTo(parent.start)
                        }
                        .padding(top = AppTheme.dimensions.paddingMedium),
                )

                TextBody(
                    text = user.age.toString(),
                    modifier = Modifier.constrainAs(ageValue) {
                        top.linkTo(ageLabel.bottom)
                        start.linkTo(ageLabel.start)
                    },
                )

                TextSubtitle(
                    text = stringResource(id = R.string.user_list_user_card_id_label),
                    modifier = Modifier
                        .constrainAs(idLabel) {
                            top.linkTo(ageLabel.top)
                            start.linkTo(ageLabel.end)
                        }
                        .padding(
                            start = AppTheme.dimensions.paddingMedium,
                            top = AppTheme.dimensions.paddingMedium
                        ),
                )

                TextBody(
                    text = user.id.toString(),
                    modifier = Modifier
                        .constrainAs(idValue) {
                            top.linkTo(idLabel.bottom)
                            start.linkTo(idLabel.start)
                        }
                        .padding(start = AppTheme.dimensions.paddingMedium),
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_UsersScreen() {
    ListUsersAvantsoftTheme {
        UsersScreen(
            navController = rememberNavController(),
            uiState = UserUiState.Listing(
                // TODO Create mock file
                users = mockedUsers.chunked(10),
            ),
        )
    }
}

private val mockedUsers = arrayListOf<User>().apply {
    for (i in 0 until 50) {
        add(
            User(
                id = i,
                age = 20 + i,
                name = "Name user $i",
                email = "$i-Email@email.com",
            )
        )
    }
}