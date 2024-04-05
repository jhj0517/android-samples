package com.jhj0517.custom_tooltip_window.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jhj0517.custom_tooltip_window.databinding.ItemExampleBinding
import com.jhj0517.custom_tooltip_window.models.ExampleData

class ExampleAdapter (private val clickListener: RecyclerClickInterface<ExampleData>)
    : ListAdapter<ExampleData, ExampleAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(val binding: ItemExampleBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(items: ExampleData){
            with(binding){
                tvName.text = items.name
                ivImage.setImageResource(items.image)
            }
        }

        init {
            with(binding){
                root.setOnClickListener {
                    val item = currentList[adapterPosition]
                    clickListener.onClick(item, root)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemExampleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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