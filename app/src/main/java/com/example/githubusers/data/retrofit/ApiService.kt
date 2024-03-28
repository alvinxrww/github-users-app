package com.example.githubusers.data.retrofit

import com.example.githubusers.BuildConfig
import com.example.githubusers.data.response.UserItem
import com.example.githubusers.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("users")
    @Headers("Authorization: token ${BuildConfig.API_TOKEN}")
    fun getUsers(): Call<List<UserItem>>

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.API_TOKEN}")
    fun getUsersByUname(
        @Query("q") username: String
    ): Call<UserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.API_TOKEN}")
    fun getFollowers(@Path("username") username: String): Call<List<UserItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.API_TOKEN}")
    fun getFollowing(@Path("username") username: String): Call<List<UserItem>>
}