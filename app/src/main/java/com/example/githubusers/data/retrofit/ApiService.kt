package com.example.githubusers.data.retrofit

import com.example.githubusers.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    fun getUsers(username: String): Call<UserResponse> {
        return getUsersIf(username)
    }

    @GET("search/users")
    fun getUsersIf(
        @Query("username") username: String
    ): Call<UserResponse>
}