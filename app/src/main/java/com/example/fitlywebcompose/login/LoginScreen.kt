package com.example.fitlywebcompose.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitlywebcompose.R
import com.example.fitlywebcompose.data.getViewModelFactory
import com.example.fitlywebcompose.login.mvi.LoginViewModel
import com.example.fitlywebcompose.ui.theme.Typography
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay

@Preview
@Composable
fun LoginScreen(
    onNavigateToRoutineScreen: () -> Unit={},
    viewModel: LoginViewModel= viewModel(factory= getViewModelFactory())
) {


        var user by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        var isError by rememberSaveable {
            mutableStateOf(false)
        }
        var newTry by remember {
            mutableStateOf(false)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .padding(4.dp)
                    .width(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Row() {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 25.sp
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "accountCircle",
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = stringResource(R.string.sign_in),
                fontSize = 20.sp
            )


            OutlinedTextField(
                value = user,
                onValueChange = { user = it },
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(4.dp)
                    .width(300.dp),
                label = { Text(text = stringResource(R.string.username)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "accountBox"
                    )
                },
                singleLine = true,
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(4.dp)
                    .width(300.dp),
                label = { Text(text = stringResource(R.string.password)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "lock"
                    )
                },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                }
            )


            if (viewModel.uiState.isFetching) {
                CircularIndeterminateProgressBar(isDisplayed = viewModel.uiState.isFetching)
                isError = false
            } else {
                Log.v(null,"refresh")
                if (viewModel.uiState.isAuthenticated && newTry) {
                    newTry = false
                    onNavigateToRoutineScreen()
                } else if (newTry) {
                    isError = true
                    newTry = false
                }
                Button(
                    onClick = {
                        viewModel.doLogin(user, password)
                        newTry = true
                    },
                    enabled = user.isNotEmpty() && password.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = stringResource(R.string.LoginButton),
                        fontSize = Typography.body1.fontSize,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }

            if (isError) {
                Snackbar(
                    backgroundColor = MaterialTheme.colors.error,
                    modifier = Modifier.width(300.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = viewModel.uiState.error.toString(),
                            color = MaterialTheme.colors.onError,
                            modifier = Modifier.weight(3f)
                        )
                        TextButton(
                            onClick = {
                                isError = false
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "CLOSE")
                        }
                    }
                }
            }

        }
    }

