package com.example.applogin

import java.util.Date

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val dateOfBirth: Date,
    val picture: String
)

