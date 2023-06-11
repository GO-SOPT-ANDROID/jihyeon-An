package org.go.sopt.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Gallery
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seminar1.R
import com.example.seminar1.databinding.RvGithubItemBinding

class GalleryRVAdapter : ListAdapter<GalleryItem, GalleryRVAdapter.ViewHolder>(diffUtil) {

    private var selectedGalleryItem = arrayListOf<GalleryItem>()
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GalleryItem>() {

            // 두 아이템이 동일한 아이템인지 체크. 보통 고유한 id를 기준으로 비교
            override fun areItemsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
                return oldItem == newItem
            }

            // 두 아이템이 동일한 내용을 가지고 있는지 체크. areItemsTheSame()이 true일때 호출됨
            override fun areContentsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvGithubItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


    private fun applySelection(binding: RvGithubItemBinding, galleryItem: GalleryItem){
        if(selectedGalleryItem.contains(galleryItem)){
            selectedGalleryItem.remove(galleryItem)
            changeBackground(binding, R.color.yellow)
        } else{
            selectedGalleryItem.add(galleryItem)
            changeBackground(binding, R.color.green)
        }
    }

    private fun changeBackground(binding: RvGithubItemBinding, resId: Int){
        binding.background.setBackgroundColor(ContextCompat.getColor(binding.root.context, resId))
    }

    private var onItemClickListener : ((GalleryItem)-> Unit) ? = null
    fun setOnItemClickListener(listener: (GalleryItem) -> Unit) {
        onItemClickListener = listener
    }
    fun getSelectedItem() = selectedGalleryItem.size

    fun getSelectedItemList() = selectedGalleryItem

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    inner class ViewHolder(private val binding: RvGithubItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GalleryItem) {
            binding.tvTitle.text = item.title
            binding.tvName.text = item.name
            binding.root.setOnClickListener {
                applySelection(binding, item)
                onItemClickListener?.let { it(item) }
            }
        }
    }
}

data class GalleryItem(
    val title: String,
    val name: String
)