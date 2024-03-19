package com.example.viewpager2_with_logic.viewmodels

import com.example.viewpager2_with_logic.datas.ParentData

class ParentAdapterViewModel(
    private val item: ParentData
) {
    val header:String
        get() = item.header
}