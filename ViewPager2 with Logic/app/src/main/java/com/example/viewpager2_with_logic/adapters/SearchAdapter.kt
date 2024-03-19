package com.example.viewpager2_with_logic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager2_with_logic.databinding.ItemExampleBinding
import com.example.viewpager2_with_logic.datas.ExampleData

class SearchAdapter
: ListAdapter<ExampleData, SearchAdapter.ViewHolder>(diffUtil),
Filterable {

    private var originList = arrayListOf<ExampleData>()

    private val searchFilter : Filter = object : Filter() {
        override fun performFiltering(input: CharSequence): FilterResults {
            val filterPattern = input.toString().lowercase().trim { it <= ' '}

            val filteredList = if (filterPattern.isEmpty()) {
                originList
            } else {
                originList.filter { it.name.lowercase().contains(filterPattern) }
            }
            return FilterResults().apply { values = filteredList }
        }

        override fun publishResults(input: CharSequence, results: FilterResults) {
            submitList(results.values as ArrayList<ExampleData>)
        }
    }

    inner class ViewHolder(val binding:ItemExampleBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(items: ExampleData){
            with(binding){
                name.text = items.name
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

    override fun getFilter(): Filter {
        return searchFilter
    }

    fun setData(list: ArrayList<ExampleData>?){
        this.originList = list!!
        submitList(list)
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