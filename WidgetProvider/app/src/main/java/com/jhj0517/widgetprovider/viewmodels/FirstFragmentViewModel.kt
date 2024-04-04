package com.jhj0517.widgetprovider.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jhj0517.widgetprovider.R
import com.jhj0517.widgetprovider.models.ExampleData
import com.jhj0517.widgetprovider.repositories.FruitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject



const val updateInterval = 2000L // 2 seconds
@HiltViewModel
class FirstFragmentViewModel @Inject constructor(
    repository: FruitsRepository
): ViewModel(){

    private val _latestFruit = repository.latestFruit.asLiveData()
    val latestFruit get() = _latestFruit

}