package com.example.githubusers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.data.response.UserDetailResponse
import com.example.githubusers.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {
    var username = ""
        set(value) {
            // Update the value of username and trigger fetching followings
            field = value
            getDetails(username)
        }

    private val _details = MutableLiveData<UserDetailResponse>()
    val details: MutableLiveData<UserDetailResponse> = _details

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        getDetails(username)
    }

    private fun getDetails(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<UserDetailResponse> {
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _details.value = response.body()
                }
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Failed to fetch users: ${t.message}"
            }
        })
    }
}