package com.example.githubusers.data.local.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.data.local.database.FavoriteUser
import com.example.githubusers.data.local.database.FavoriteUserDao
import com.example.githubusers.data.local.database.FavoriteUserRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteUserRepository(application: Application): ViewModel() {
    private val mFavoriteUserDao: FavoriteUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = FavoriteUserRoomDatabase.getDatabase(application)
        mFavoriteUserDao = db.favoriteUserDao()
    }
    fun getAllFavoriteUser(): LiveData<List<FavoriteUser>> = mFavoriteUserDao.getAllFavoriteUser()
    fun insert(favoriteUser: FavoriteUser) {
        executorService.execute { mFavoriteUserDao.insert(favoriteUser) }
    }
    fun delete(favoriteUser: FavoriteUser) {
        executorService.execute { mFavoriteUserDao.delete(favoriteUser) }
    }
    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUser> {
        return mFavoriteUserDao.getFavoriteUserByUsername(username)
    }
}