package com.example.githubusers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.data.response.UserItem
import com.example.githubusers.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {
    private val followingViewModel: FollowingViewModel by viewModels<FollowingViewModel>()
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }

    private var position = 1
    private var username = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)!!

            followingViewModel.username = username
        }
        if (position == 1){
            binding.testUsername.text = "Get Following $username"
            followingViewModel.findFollowing(username)
        } else {
            binding.testUsername.text = "Get Follower $username"
            followingViewModel.findFollower(username)
        }

        followingViewModel.followings.observe(viewLifecycleOwner) { user ->
            setUserData(user)
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollowing.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFollowing.addItemDecoration(itemDecoration)

        followingViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setUserData(users: List<UserItem>) {
        val adapter = UserAdapter()
        adapter.submitList(users)
        binding.rvFollowing.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}