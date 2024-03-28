package com.example.githubusers.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusers.data.response.UserItem
import com.example.githubusers.databinding.UserRowItemBinding

class UserAdapter : ListAdapter<UserItem, UserAdapter.ListViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = UserRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = getItem(position)
        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .circleCrop()
            .into(holder.binding.imgItemPhoto)

        val username = user.login
        val image = user.avatarUrl
        holder.binding.tvItemUsername.text = username
        holder.itemView.setOnClickListener {
//            Toast.makeText(
//                holder.itemView.context,
//                "You chose $username",
//                Toast.LENGTH_SHORT
//            ).show()

            val detailIntent = Intent(holder.itemView.context, DetailsActivity::class.java)
            detailIntent.putExtra(DetailsActivity.USERNAME, username)
            detailIntent.putExtra(DetailsActivity.IMAGE, image)
            holder.itemView.context.startActivity(detailIntent)
        }
    }

    class ListViewHolder(var binding: UserRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}