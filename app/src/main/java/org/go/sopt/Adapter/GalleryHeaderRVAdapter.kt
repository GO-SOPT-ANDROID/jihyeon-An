package org.go.sopt.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seminar1.databinding.RvGithubHeaderItemBinding

class GalleryHeaderRVAdapter : ListAdapter<String, GalleryHeaderRVAdapter.ViewHolder>(diffUtil){

    class ViewHolder(private val binding : RvGithubHeaderItemBinding): RecyclerView.ViewHolder(binding.root){}

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem === newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvGithubHeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}
}