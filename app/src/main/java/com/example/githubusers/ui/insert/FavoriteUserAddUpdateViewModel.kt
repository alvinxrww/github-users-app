package com.example.githubusers.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.githubusers.data.database.FavoriteUser
import com.example.githubusers.data.repository.FavoriteUserRepository

class FavoriteUserAddUpdateViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)
    fun insert(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.insert(favoriteUser)
    }
    fun update(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.update(favoriteUser)
    }
    fun delete(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.delete(favoriteUser)
    }
}