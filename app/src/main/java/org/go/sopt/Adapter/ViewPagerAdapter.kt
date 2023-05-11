package org.go.sopt.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seminar1.databinding.ItemGalleryPagerBinding

class ViewPagerAdapter(
    _itemList: List<Int> = listOf()
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    private var itemList: List<Int> = _itemList

    class ViewPagerViewHolder(
        private val binding : ItemGalleryPagerBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(src: Int){
            binding.itemImageView.setImageResource(src)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemGalleryPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun setItemList(itemList: List<Int>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }
}