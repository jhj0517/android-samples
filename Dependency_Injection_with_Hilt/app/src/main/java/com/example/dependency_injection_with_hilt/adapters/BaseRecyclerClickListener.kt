package com.example.dependency_injection_with_hilt.adapters

interface BaseRecyclerClickListener<T> {

    fun onItemClick(item: T)
    fun onDelete(item: T)

}