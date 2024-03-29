package com.example.githubusers.ui

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubusers.data.database.FavoriteUser
import com.example.githubusers.data.repository.FavoriteUserRepository

class FavoriteViewModel(application: Application) {
    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)
    fun getAllFavoriteUser(): LiveData<List<FavoriteUser>> = mFavoriteUserRepository.getAllFavoriteUser()

    fun insert(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.insert(favoriteUser)
    }
}