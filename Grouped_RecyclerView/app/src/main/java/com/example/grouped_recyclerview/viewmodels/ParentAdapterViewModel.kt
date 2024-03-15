package com.example.grouped_recyclerview.viewmodels

import com.example.grouped_recyclerview.datas.ParentData

class ParentAdapterViewModel(
    private val item:ParentData
) {
    val header:String
        get() = item.header
}