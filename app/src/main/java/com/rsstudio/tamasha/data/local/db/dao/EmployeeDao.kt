package com.rsstudio.tamasha.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rsstudio.tamasha.data.network.model.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployeesData(employeeData: List<Data>)

    @Query("DELETE FROM employee_data")
    suspend fun deleteEmployeeData()

    @Query("SELECT * FROM employee_data")
    fun getEmployeeData(): Flow<List<Data>>
}