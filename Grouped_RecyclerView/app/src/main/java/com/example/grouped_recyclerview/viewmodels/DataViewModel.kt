package com.example.grouped_recyclerview.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grouped_recyclerview.datas.ExampleData
import com.example.grouped_recyclerview.datas.ParentData

class DataViewModel:ViewModel() {

    private val _exampleRawData = MutableLiveData<ArrayList<ExampleData>>()
    val exampleRawData get() = _exampleRawData

    private val _exampleGroupData = MutableLiveData<ArrayList<ParentData>>()
    val exampleGroupData get() = _exampleGroupData

    init {
        fetchRawData()
        divideGroups()
    }

    private fun fetchRawData() {
        // Suppose this fetches data from somewhere
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
        exampleRawData.value = fetched
    }

    private fun divideGroups() {
        val list = arrayListOf<ParentData>()
        val groups = exampleRawData.value!!.groupBy {
            it.name.substring(0,1)
        }
        groups.forEach {(k,v) ->
            list.add(
                ParentData(
                    header = k,
                    childList = ArrayList(v)
                )
            )
        }
        _exampleGroupData.value = list
    }

}