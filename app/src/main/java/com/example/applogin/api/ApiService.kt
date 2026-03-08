package com.example.applogin.api

import com.example.applogin.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}

