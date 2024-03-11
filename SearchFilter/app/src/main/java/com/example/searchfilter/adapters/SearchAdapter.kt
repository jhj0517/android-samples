package com.example.searchfilter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchfilter.databinding.ItemExampleBinding
import com.example.searchfilter.datas.ExampleData

class SearchAdapter
    : ListAdapter<ExampleData, SearchAdapter.ViewHolder>(diffUtil),
    Filterable {

    private var originList = arrayListOf<ExampleData>()

    private val searchFilter : Filter = object : Filter() {
        override fun performFiltering(input: CharSequence): FilterResults {

            val filteredList: ArrayList<ExampleData> = ArrayList()
            if (input.isEmpty()) {
                filteredList.addAll(originList)
            } else {
                val filterPattern = input.toString().lowercase().trim { it <= ' '}
                for (item in originList) {
                    if(item.name.lowercase().contains(filterPattern)){
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results

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