package com.jhj0517.viewbinding_and_databinding.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhj0517.viewbinding_and_databinding.models.ExampleData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DataViewModel: ViewModel() {

    private val _exampleData = MutableLiveData<ArrayList<ExampleData>>()
    val exampleData get() = _exampleData

    init {
        fetchData()
    }

    fun fetchData() {
        // Fetches data from empty array list.
        viewModelScope.launch {
            _exampleData.value = arrayListOf()
            delay(2000L)
            _exampleData.value = arrayListOf(
                ExampleData("Grape"),
                ExampleData("Apple"),
                ExampleData("Banana"),
                ExampleData("Orange"),
                ExampleData("Peach"),
                ExampleData("Plum"),
                ExampleData("Pear"),
            )
        }
    }

}