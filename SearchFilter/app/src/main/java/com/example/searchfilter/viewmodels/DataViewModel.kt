package com.example.searchfilter.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchfilter.datas.ExampleData

class DataViewModel:ViewModel() {

    private val _exampleData = MutableLiveData<ArrayList<ExampleData>>()
    val exampleData get() = _exampleData

    init {
        fetchData()
    }

    private fun fetchData() {
        // Suppose this function fetches data from somewhere
        val fetched = ArrayList(
            listOf(
                ExampleData("Grape"),
                ExampleData("Apple"),
                ExampleData("Banana"),
                ExampleData("Orange"),
                ExampleData("Peach"),
                ExampleData("Plum"),
                ExampleData("Pear"),
                ExampleData("Cherry"),
                ExampleData("Strawberry"),
                ExampleData("Melon"),
                ExampleData("Great"),
                ExampleData("Grate"),
                ExampleData("Grade"),
                ExampleData("Grapes"),
                ExampleData("Grain"),
                ExampleData("Greet"),
                ExampleData("Grease"),
            )
        )
        _exampleData.value = fetched
    }

}