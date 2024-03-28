package com.example.githubusers.data.retrofit

import com.example.githubusers.data.response.UserDetailResponse
import com.example.githubusers.data.response.UserItem
import com.example.githubusers.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("users")
    fun getUsers(): Call<List<UserItem>>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<UserDetailResponse>

    @GET("search/users")
    fun getUsersByUname(
        @Query("q") username: String
    ): Call<UserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<UserItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<UserItem>>
}