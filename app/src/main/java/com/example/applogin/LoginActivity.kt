package com.example.applogin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.applogin.ui.theme.AppLoginTheme

@Composable
fun Login(modifier: Modifier = Modifier, navController: NavController, registeredUsers: MutableList<User>) {
    var usernameTextFieldValue by remember { mutableStateOf("") }
    var passwordTextFieldValue by remember { mutableStateOf("") }
    var rememberMeChecked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login Screen")
        TextField(
            value = usernameTextFieldValue,
            onValueChange = {text -> usernameTextFieldValue = text},
            label = { Text("Username") }
        )
        TextField(
            value = passwordTextFieldValue,
            onValueChange = {text -> passwordTextFieldValue = text},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = rememberMeChecked,
                onCheckedChange = { checked -> rememberMeChecked = checked }
            )
            Text(text = "Remember Me")
        }
        Button(onClick = {
            val user = registeredUsers.find { it.username == usernameTextFieldValue && it.password == passwordTextFieldValue }
            if (user != null) {
                print("Login successful for user: ${user.username}")
            } else {
                print("Invalid username or password")
            }
        }) {
            Text(text = "Login")
        }
        Text(text = "Don't have an account? Sign Up", modifier = Modifier.clickable(onClick = {
            navController.navigate("register")
        }))
    }

}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    AppLoginTheme {
        Login(navController = rememberNavController(), registeredUsers = mutableListOf())
    }
}