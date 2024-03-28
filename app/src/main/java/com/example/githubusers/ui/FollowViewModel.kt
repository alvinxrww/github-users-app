package com.example.githubusers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.data.response.UserItem
import com.example.githubusers.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel() {
    var username = ""

    private val _followings = MutableLiveData<List<UserItem>>()
    val followings: LiveData<List<UserItem>> = _followings

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun findFollowing(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _followings.value = response.body()
                } else {
                    _errorMessage.value = "Failed to fetch users: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Failed to fetch users: ${t.message}"
            }
        })
    }

    fun findFollower(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _followings.value = response.body()
                } else {
                    _errorMessage.value = "Failed to fetch users: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Failed to fetch users: ${t.message}"
            }
        })
    }
}