package com.rsstudio.tamasha.data.network.apis

import com.rsstudio.tamasha.data.network.model.Employee
import retrofit2.http.GET

interface EmployeeApiInterface {

    @GET("t_app.json")
    suspend fun getEmployees(
    ): Employee
}