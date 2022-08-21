package com.rsstudio.tamasha.data.local.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rsstudio.tamasha.data.local.db.dao.EmployeeDao
import com.rsstudio.tamasha.data.network.model.Data

@Database(entities = [Data::class], version = 1)
abstract class EmployeeDatabase: RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao
}