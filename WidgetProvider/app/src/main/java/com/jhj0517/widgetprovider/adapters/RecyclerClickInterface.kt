package com.jhj0517.widgetprovider.adapters
interface BaseRecyclerClickListener<T> {

    fun onItemClick(item: T)
    fun onDelete(item: T)

}