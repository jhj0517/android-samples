package com.jhj0517.room.adapters

interface BaseRecyclerClickListener<T> {

    fun onItemClick(item: T)
    fun onDelete(item: T)

}