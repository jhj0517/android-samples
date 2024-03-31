package com.example.dependency_injection_with_hilt.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dependency_injection_with_hilt.localdb.DataDao
import com.example.dependency_injection_with_hilt.models.ExampleData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel: ViewModel(){

    private val _exampleDataList = MutableLiveData<List<ExampleData>>()
    val exampleDataList get() = _exampleDataList

    // You should not pass Dao just like this. Use Hilt instead.
    fun getLocalData(dataDao: DataDao){
        // According to official guides here : https://developer.android.com/kotlin/coroutines/coroutines-adv?authuser=2#main-safety
        // Using room components should be dealt with in Dispatchers.IO
        viewModelScope.launch(Dispatchers.Main) {
            // You should use `postValue` instead of `setValue` on Dispatchers.IO
            _exampleDataList.postValue(dataDao.getAllData())
        }
    }

    fun insertLocalData(dataDao: DataDao, data:ExampleData){
        viewModelScope.launch(Dispatchers.IO) {
            dataDao.insertData(data)
            _exampleDataList.postValue(dataDao.getAllData())
        }
    }

    fun deleteLocalData(dataDao: DataDao, data:ExampleData){
        viewModelScope.launch(Dispatchers.IO) {
            dataDao.deleteData(data)
            _exampleDataList.postValue(dataDao.getAllData())
        }
    }
}