package com.example.githubusers.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.data.response.UserDetailResponse
import com.example.githubusers.data.response.UserItem
import com.example.githubusers.databinding.ActivityDetailsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModels<DetailsViewModel>()


    companion object {
        const val USERNAME = "username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_title_1,
            R.string.tab_title_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        detailsViewModel.details.observe(this) { detail ->
            setDetailsData(detail)
        }

        val username = intent.getStringExtra(USERNAME)
        detailsViewModel.username = username!!
        detailsViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager

        viewPager.adapter = sectionsPagerAdapter
        sectionsPagerAdapter.username = username

        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }

    private fun setDetailsData(details: UserDetailResponse) {
        val image = details.avatarUrl
        val userName = details.login
        val name = details.name
        val followingCount = details.following
        val followersCount = details.followers

        Glide.with(binding.root)
            .load(image)
            .circleCrop()
            .into(binding.imgItemPhoto)
        binding.tvItemUsername.text = userName
        binding.tvItemName.text = name
        binding.tvItemFolls.text = "$followingCount following               |               $followersCount followers"
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}