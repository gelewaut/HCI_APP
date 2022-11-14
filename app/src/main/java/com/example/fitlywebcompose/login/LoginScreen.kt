package com.example.fitlywebcompose.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitlywebcompose.R
import com.example.fitlywebcompose.ui.theme.Typography

@Preview(showBackground = true, widthDp = 400, heightDp = 400)
@Composable
fun LoginScreen(
    onNavigateToRoutineScreen: () -> Unit={}
) {

        var user by rememberSaveable{ mutableStateOf("") }
        var password by rememberSaveable{ mutableStateOf("") }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .width(300.dp),
                contentAlignment = Alignment.Center
            ){
                Row() {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 25.sp
                    )
//                    Icon(imageVector = Icons.Default.Lock,
//                        contentDescription = "lock"
//                    )
                }
            }
            Row(modifier= Modifier.padding(4.dp)) {
                Text(text = stringResource(R.string.sign_in))
                Icon(imageVector = Icons.Default.Login, contentDescription = "accountBox")
            }
            
            OutlinedTextField(
                value = user,
                onValueChange = {user = it},
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .width(300.dp),
                label = { Text(text = stringResource(R.string.username)) },
                placeholder = { Text(text = stringResource(R.string.insert_username))},
                leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "accountCircle") },
                singleLine = true,
            )
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .width(300.dp),
                label = { Text(text =  stringResource(R.string.password))},
                placeholder = { Text(text = stringResource(R.string.insert_password) )},
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lock") },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, description)
                    }
                }
            )
            Button(
                onClick = { onNavigateToRoutineScreen() },
                enabled = user.isNotEmpty() && password.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary))              {
                Text(
                    text = stringResource(R.string.LoginButton),
                    fontSize = Typography.body1.fontSize,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
