package com.jhj0517.room.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhj0517.room.localdb.DataDao
import com.jhj0517.room.models.ExampleData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel: ViewModel(){

    private val _exampleDataList = MutableLiveData<List<ExampleData>>()
    val exampleDataList get() = _exampleDataList

    fun getLocalData(dataDao: DataDao){
        viewModelScope.launch(Dispatchers.IO) {
            _exampleDataList.postValue(dataDao.getAllData()) // can not setValue on Dispatchers.IO
        }
    }

    fun insertLocalData(dataDao: DataDao, data:ExampleData){
        viewModelScope.launch {
            dataDao.insertData(data)
        }
        getLocalData(dataDao)
    }
}