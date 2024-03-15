package com.example.grouped_recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.grouped_recyclerview.databinding.ItemExampleBinding
import com.example.grouped_recyclerview.datas.ExampleData
import com.example.grouped_recyclerview.datas.ParentData
import com.example.grouped_recyclerview.viewmodels.ParentAdapterViewModel

class ChildAdapter
    : ListAdapter<ExampleData, ChildAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemExampleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: ExampleData) {
            with(binding) {
                name.text = items.name
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemExampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ExampleData>() {
            override fun areContentsTheSame(oldItem: ExampleData, newItem: ExampleData) =
                oldItem.name == newItem.name

            override fun areItemsTheSame(oldItem: ExampleData, newItem: ExampleData) =
                oldItem.name == newItem.name
        }
    }
}