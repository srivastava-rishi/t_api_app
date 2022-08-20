package com.rsstudio.tamasha.ui.main.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsstudio.tamasha.data.network.model.Employee
import com.rsstudio.tamasha.data.network.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: MainRepository
) : ViewModel() {

    var logTag = "@MainViewModel"

    // the pattern that i usually follow
    private val _employeeData: MutableLiveData<Employee> = MutableLiveData()
    val employeeData: LiveData<Employee> get() = _employeeData


    init {
        getEmployeeData()
    }

    private fun getEmployeeData() {

        viewModelScope.launch {

            val result = repository.getEmployeeData()

            Log.d(logTag, "getAthleteData: $result")

            _employeeData.value = result

        }
    }

}