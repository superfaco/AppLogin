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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.applogin.ui.theme.AppLoginTheme


@Composable
fun Register(modifier: Modifier = Modifier, navController: NavController) {
    var emailTextFieldValue by remember { mutableStateOf("") }
    var usernameTextFieldValue by remember { mutableStateOf("") }
    var passwordTextFieldValue by remember { mutableStateOf("") }
    var confirmPasswordTextFieldValue by remember { mutableStateOf("") }

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
            label = { Text("Password") }
        )
        TextField(
            value = confirmPasswordTextFieldValue,
            onValueChange = { text -> confirmPasswordTextFieldValue = text },
            label = { Text("Confirm Password") }
        )
        Button(onClick = {
            print("Register with email: $emailTextFieldValue, username: $usernameTextFieldValue, password: $passwordTextFieldValue")
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
        Register(navController = rememberNavController())
    }
}

