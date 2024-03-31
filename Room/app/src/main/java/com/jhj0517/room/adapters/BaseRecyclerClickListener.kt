package com.jhj0517.room.adapters

abstract class BaseRecyclerClickListener<T> {

    abstract fun onItemClick(item: T)
    abstract fun onDelete(item: T)

}