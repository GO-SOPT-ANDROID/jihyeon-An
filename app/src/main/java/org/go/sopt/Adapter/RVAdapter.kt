package org.go.sopt.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seminar1.R
import com.example.seminar1.databinding.RvGithubHeaderItemBinding
import com.example.seminar1.databinding.RvGithubItemBinding
import org.go.sopt.RVItem

private const val HEADER = 0
private const val ITEM = 1

class RVAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context)}
    private var itemList : List<RVItem> = emptyList()

    class ItemViewHolder(
        private val binding : RvGithubItemBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(data : RVItem){
            binding.tvName.text = data.name
            binding.tvTitle.text = data.title
        }
    }

    class HeaderViewHolder(
        private val binding : RvGithubHeaderItemBinding
    ) : RecyclerView.ViewHolder(binding.root){}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemBinding = RvGithubItemBinding.inflate(inflater, parent, false)
        val headerBinding = RvGithubHeaderItemBinding.inflate(inflater, parent, false)

        return when(viewType){
            HEADER -> HeaderViewHolder(headerBinding)
            else -> ItemViewHolder(itemBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HeaderViewHolder -> {}
            is ItemViewHolder -> {
                holder.bind(itemList[position - 1])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) HEADER else ITEM
    }

    override fun getItemCount(): Int {
        return itemList.size + 1
    }

    fun setRvItemList(itemList : List<RVItem>){
        this.itemList = itemList
        notifyDataSetChanged()
    }

}