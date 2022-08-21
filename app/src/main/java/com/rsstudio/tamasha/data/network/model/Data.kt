package com.rsstudio.tamasha.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_data")
data class Data(
    @PrimaryKey(autoGenerate = false)val id: Int,
    val employee_name: String,
    val profile_image: String,
    val employee_salary: Int,
    val employee_age: Int,
    var visibility: Boolean = false
)