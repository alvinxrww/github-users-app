package com.example.githubusers.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.data.response.UserItem
import com.example.githubusers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        mainViewModel.users.observe(this) { user ->
            setUserData(user)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        mainViewModel.errorMessage.observe(this) { errorMessage ->
            Toast.makeText(binding.root.context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecoration)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        val username = textView.text.toString()
                        mainViewModel.findUsers(username)
                        searchBar.setText(username)
                        searchView.hide()
                        true
                    } else {
                        false
                    }
                }
        }
    }

    private fun setUserData(users: List<UserItem>) {
        val adapter = UserAdapter()
        adapter.submitList(users)
        binding.rvUsers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}