package com.jhj0517.custom_tooltip_window.adapters

import android.view.View

interface RecyclerClickInterface<T> {

    fun onClick(item: T, view: View)

}