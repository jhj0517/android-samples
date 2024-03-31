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

    // You should not pass Dao just like this. You should use Hilt instead.
    // For the Hilt-using version, see : https://github.com/jhj0517/Android-Practices/tree/master/Dependency_Injection_with_Hilt
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