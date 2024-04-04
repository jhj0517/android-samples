package com.jhj0517.widgetprovider.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jhj0517.widgetprovider.repositories.FruitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FirstFragmentViewModel @Inject constructor(
    repository: FruitsRepository
): ViewModel(){

    private val _latestFruit = repository.latestFruit.asLiveData()
    val latestFruit get() = _latestFruit

}