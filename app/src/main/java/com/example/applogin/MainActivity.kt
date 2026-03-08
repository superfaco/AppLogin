package com.example.applogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applogin.api.RetrofitClient
import com.example.applogin.ui.theme.AppLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLoginTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val registeredUsers = remember { mutableStateOf(mutableListOf<User>()) }

                    LaunchedEffect(Unit) {
                        try {
                            val users = RetrofitClient.apiService.getUsers()
                            registeredUsers.value = users.toMutableList()
                        } catch (e: Exception) {
                            print("Error loading users: ${e.message}")
                        }
                    }

                    AppNavigation(navController = navController, modifier = Modifier.padding(innerPadding), registeredUsers = registeredUsers.value)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier, registeredUsers: MutableList<User>) {
    NavHost(navController = navController, startDestination = "login", modifier = modifier) {
        composable("login") {
            Login(navController = navController, registeredUsers = registeredUsers)
        }
        composable("register") {
            Register(navController = navController, registeredUsers = registeredUsers)
        }
    }
}

