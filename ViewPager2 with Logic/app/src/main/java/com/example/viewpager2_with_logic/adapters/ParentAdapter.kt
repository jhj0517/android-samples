package com.example.viewpager2_with_logic.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager2_with_logic.databinding.ItemParentBinding
import com.example.viewpager2_with_logic.datas.ParentData
import com.example.viewpager2_with_logic.viewmodels.ParentAdapterViewModel

class ParentAdapter(
    private val context: Context
) : ListAdapter<ParentData, ParentAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemParentBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(items: ParentData){
            with(binding){
                viewModel = ParentAdapterViewModel(items)
                executePendingBindings()

                val adapter = SearchAdapter()
                rvChild.adapter = adapter
                rvChild.layoutManager = GridLayoutManager(context, 3)
                adapter.submitList(items.childList)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemParentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ParentData>() {
            override fun areContentsTheSame(oldItem: ParentData, newItem: ParentData) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ParentData, newItem: ParentData) =
                oldItem == newItem
        }
    }
}