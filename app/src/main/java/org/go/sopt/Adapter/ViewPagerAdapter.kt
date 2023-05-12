package org.go.sopt.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seminar1.databinding.ItemViewPagerBinding
import org.go.sopt.model.ResponseUser

class ViewPagerAdapter(
    _itemList: List<ResponseUser.Data> = listOf()
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    private var itemList: List<ResponseUser.Data> = _itemList

    class ViewPagerViewHolder(
        private val binding : ItemViewPagerBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ResponseUser.Data){
            binding.tvName.text = item.first_name
            binding.tvEmail.text = item.email
            Glide.with(binding.root)
                .load(item.avatar)
                .into(binding.imagePerson)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun setItemList(itemList: List<ResponseUser.Data>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }
}