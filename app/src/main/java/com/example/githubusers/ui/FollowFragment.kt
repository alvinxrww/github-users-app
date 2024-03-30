package com.example.githubusers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.data.response.UserItem
import com.example.githubusers.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {
    private val followViewModel: FollowViewModel by viewModels<FollowViewModel>()
    private var _binding: FragmentFollowBinding? = null

//    sebagaimana pada dokumentasi developer android berikut:
//    https://developer.android.com/topic/libraries/view-binding
//    memang binding untuk fragment diambil dengan operator double bang
    private val binding get() = _binding!!

    private var position = 1
    private var username = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)!!

            followViewModel.username = username
        }
        if (position == 1){
            followViewModel.findFollowing(username)
        } else {
            followViewModel.findFollower(username)
        }

        followViewModel.followings.observe(viewLifecycleOwner) { user ->
            setUserData(user)
        }

        followViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        followViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollowing.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFollowing.addItemDecoration(itemDecoration)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
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

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }
}