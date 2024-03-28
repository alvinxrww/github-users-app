package com.example.githubusers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.data.response.UserItem
import com.example.githubusers.data.response.UserResponse
import com.example.githubusers.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _users = MutableLiveData<List<UserItem>>()
    val users: LiveData<List<UserItem>> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        findUsers()
    }

    private fun findUsers() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsers()
        client.enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _users.value = response.body()
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

    fun findUsers(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsersByUname(username)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _users.value = response.body()?.items
                } else {
                    _errorMessage.value = "Failed to fetch users: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Failed to fetch users: ${t.message}"
            }
        })
    }
}