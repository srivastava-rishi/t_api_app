package com.rsstudio.tamasha.data.network.repository

import com.rsstudio.tamasha.data.network.apis.EmployeeApiInterface
import com.rsstudio.tamasha.data.network.model.Employee
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val api: EmployeeApiInterface) {

    suspend fun getEmployeeData(): Employee {

        return api.getEmployees()

    }

}