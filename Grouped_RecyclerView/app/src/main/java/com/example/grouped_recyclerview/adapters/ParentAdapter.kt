package com.example.grouped_recyclerview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.grouped_recyclerview.databinding.ItemParentBinding
import com.example.grouped_recyclerview.datas.ParentData
import com.example.grouped_recyclerview.viewmodels.ParentAdapterViewModel

class ParentAdapter(
    private val context: Context
) : ListAdapter<ParentData, ParentAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding:ItemParentBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(items: ParentData){
            with(binding){
                viewModel = ParentAdapterViewModel(items)
                executePendingBindings()

                val adapter = ChildAdapter()
                rvChild.adapter = adapter
                rvChild.layoutManager = GridLayoutManager(context, 5)
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