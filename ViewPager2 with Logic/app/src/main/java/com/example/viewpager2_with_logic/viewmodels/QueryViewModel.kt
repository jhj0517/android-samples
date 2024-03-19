package com.example.viewpager2_with_logic.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QueryViewModel: ViewModel() {
    val _query = MutableLiveData<String>()
    val query get() = _query

    fun setQuery(queryData: String) {
        _query.value = queryData
    }
}