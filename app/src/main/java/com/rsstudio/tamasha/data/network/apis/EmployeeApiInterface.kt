package com.rsstudio.tamasha.data.network.apis

import com.rsstudio.tamasha.data.network.model.Employee
import retrofit2.http.GET

interface EmployeeApiInterface {

    @GET("61cf7d91-a7f8-405e-b505-67926b759d78")
    suspend fun getEmployees(
    ): Employee
}