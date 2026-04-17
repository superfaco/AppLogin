package com.example.applogin.api

import com.example.applogin.User
import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<User>
}
