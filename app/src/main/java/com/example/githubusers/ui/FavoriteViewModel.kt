package com.example.githubusers.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.githubusers.data.local.database.FavoriteUser
import com.example.githubusers.data.local.repository.FavoriteUserRepository

class FavoriteViewModel(application: Application) {
    private val mFavoriteUserRepository: FavoriteUserRepository =
        FavoriteUserRepository(application)

    fun getAllFavoriteUser(): LiveData<List<FavoriteUser>> =
        mFavoriteUserRepository.getAllFavoriteUser()

    fun insert(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.insert(favoriteUser)
    }

    fun delete(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.delete(favoriteUser)
    }

    fun isFavorite(username: String): LiveData<Boolean> {
        val favoriteUserLiveData = mFavoriteUserRepository.getFavoriteUserByUsername(username)

        // Create a new LiveData to hold the result of whether the user is favorite or not
        val isFavoriteLiveData = MediatorLiveData<Boolean>()

        isFavoriteLiveData.addSource(favoriteUserLiveData) { favoriteUser ->
            isFavoriteLiveData.value = favoriteUser != null
        }

        return isFavoriteLiveData
    }
}