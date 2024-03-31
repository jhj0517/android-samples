package com.example.dependency_injection_with_hilt.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dependency_injection_with_hilt.localdb.DataDao
import com.example.dependency_injection_with_hilt.models.ExampleData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val dataDao: DataDao
):ViewModel() {

    private val _exampleDataList = MutableLiveData<List<ExampleData>>()
    val exampleDataList get() = _exampleDataList

    fun getLocalData(){
        // According to official guides here : https://developer.android.com/kotlin/coroutines/coroutines-adv?authuser=2#main-safety
        // Using room components should be dealt with in Dispatchers.IO
        viewModelScope.launch(Dispatchers.IO) {
            // You should use `postValue` instead of `setValue` on Dispatchers.IO
            _exampleDataList.postValue(dataDao.getAllData())
        }
    }

    fun insertLocalData(data:ExampleData){
        viewModelScope.launch(Dispatchers.IO) {
            dataDao.insertData(data)
            _exampleDataList.postValue(dataDao.getAllData())
        }
    }

    fun deleteLocalData(data:ExampleData){
        viewModelScope.launch(Dispatchers.IO) {
            dataDao.deleteData(data)
            _exampleDataList.postValue(dataDao.getAllData())
        }
    }
}