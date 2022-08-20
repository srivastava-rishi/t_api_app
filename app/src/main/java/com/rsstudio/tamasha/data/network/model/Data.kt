package com.rsstudio.tamasha.data.network.model

data class Data(
    val id: Int,
    val employee_name: String,
    val employee_salary: Int,
    val employee_age: Int,
    var visibility: Boolean = false
)