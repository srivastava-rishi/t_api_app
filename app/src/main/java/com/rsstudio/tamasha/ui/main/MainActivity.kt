package com.rsstudio.tamasha.ui.main


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.rsstudio.tamasha.R
import com.rsstudio.tamasha.data.network.model.Employee
import com.rsstudio.tamasha.databinding.ActivityMainBinding
import com.rsstudio.tamasha.ui.base.BaseActivity
import com.rsstudio.tamasha.ui.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    var logTag = "@MainActivity"

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //
        initObservers()
    }

    private fun initObservers() {

        viewModel.employeeData.observe(this) {

            if (it != null) {
                val list: MutableList<Employee> = mutableListOf()
                Log.d(logTag, "initObservers: ${it.data}")
            }
        }

    }




}