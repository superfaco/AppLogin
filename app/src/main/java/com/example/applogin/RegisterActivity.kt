package com.example.applogin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.applogin.api.RetrofitClient
import com.example.applogin.ui.theme.AppLoginTheme
import java.util.Date


@Composable
fun Register(modifier: Modifier = Modifier, navController: NavController, registeredUsers: MutableList<User>) {
    var emailTextFieldValue by remember { mutableStateOf("") }
    var usernameTextFieldValue by remember { mutableStateOf("") }
    var passwordTextFieldValue by remember { mutableStateOf("") }
    var confirmPasswordTextFieldValue by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Register Screen")
        TextField(
            value = emailTextFieldValue,
            onValueChange = { text -> emailTextFieldValue = text },
            label = { Text("Email") }
        )
        TextField(
            value = usernameTextFieldValue,
            onValueChange = { text -> usernameTextFieldValue = text },
            label = { Text("Username") }
        )
        TextField(
            value = passwordTextFieldValue,
            onValueChange = { text -> passwordTextFieldValue = text },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        TextField(
            value = confirmPasswordTextFieldValue,
            onValueChange = { text -> confirmPasswordTextFieldValue = text },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = {
            if (emailTextFieldValue.isNotEmpty() && usernameTextFieldValue.isNotEmpty() && passwordTextFieldValue.isNotEmpty() && passwordTextFieldValue == confirmPasswordTextFieldValue) {
                val newUser = User(
                    id = registeredUsers.size + 1,
                    name = usernameTextFieldValue,
                    email = emailTextFieldValue,
                    password = passwordTextFieldValue,
                    picture = "no picture",
                    dateOfBirth = Date()
                )

                coroutineScope.launch {
                    try {
                        val savedUser = RetrofitClient.apiService.saveUser(newUser)
                        registeredUsers.add(savedUser)
                        print("User registered: $newUser")
                        navController.popBackStack()
                    } catch (e: Exception) {
                        print("Error registering user: ${e.message}")
                    }
                }
            } else {
                print("Please fill all fields and passwords must match")
            }
        }) {
            Text(text = "Register")
        }
        Text(text = "Already have an account? Login", modifier = Modifier.clickable(onClick = {
            navController.popBackStack()
        }))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    AppLoginTheme {
        Register(navController = rememberNavController(), registeredUsers = mutableListOf())
    }
}

