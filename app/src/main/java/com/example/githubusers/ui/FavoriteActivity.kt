package com.example.githubusers.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.data.response.UserItem
import com.example.githubusers.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        favoriteViewModel = FavoriteViewModel(this.application)
        val adapter = UserAdapter()
        favoriteViewModel.getAllFavoriteUser().observe(this) { users ->
            val items = arrayListOf<UserItem>()
            users.map {
                val item = UserItem(
                    login = it.username,
                    avatarUrl = it.avatarUrl ?: "",
                )
                items.add(item)
            }
            adapter.submitList(items)
            binding.rvFavorites.adapter = adapter
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavorites.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvFavorites.addItemDecoration(itemDecoration)
    }
}