package com.rsstudio.tamasha.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.rsstudio.tamasha.app.App
import com.rsstudio.tamasha.data.local.db.database.EmployeeDatabase
import com.rsstudio.tamasha.data.network.apis.EmployeeApiInterface
import com.rsstudio.tamasha.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun applicationContext(@ApplicationContext applicationContext: Context): App {
        return applicationContext as App
    }

    @Singleton
    @Provides
    fun provideEmployeeApi(): EmployeeApiInterface {

        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(EmployeeApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): EmployeeDatabase =
        Room.databaseBuilder(app, EmployeeDatabase::class.java,"employee_database")
            .build()

}