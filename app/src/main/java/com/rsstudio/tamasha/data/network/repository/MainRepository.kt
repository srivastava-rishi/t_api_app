package com.rsstudio.tamasha.data.network.repository

import androidx.room.withTransaction
import com.rsstudio.tamasha.data.local.db.database.EmployeeDatabase
import com.rsstudio.tamasha.data.network.apis.EmployeeApiInterface
import com.rsstudio.tamasha.data.network.model.Employee
import com.rsstudio.tamasha.data.network.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    private val api: EmployeeApiInterface,
    private val db: EmployeeDatabase
    ) {

    private val employeeDao = db.employeeDao()

//    suspend fun getEmployeeData(): Employee {
//
//        return api.getEmployees()
//
//    }

    fun getEmployeesData() = networkBoundResource(
        query = {
            employeeDao.getEmployeeData()
        },
        fetch = {
            delay(500)
            api.getEmployees().data

        },
        saveFetchResult = {
            db.withTransaction {
              employeeDao.deleteEmployeeData()
              employeeDao.insertEmployeesData(it)
            }
        }
    )


}