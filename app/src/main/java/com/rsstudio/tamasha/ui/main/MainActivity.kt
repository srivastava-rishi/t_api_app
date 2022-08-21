package com.rsstudio.tamasha.ui.main


import android.animation.LayoutTransition
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsstudio.tamasha.R
import com.rsstudio.tamasha.data.network.Resource
import com.rsstudio.tamasha.data.network.model.Employee
import com.rsstudio.tamasha.databinding.ActivityMainBinding
import com.rsstudio.tamasha.ui.base.BaseActivity
import com.rsstudio.tamasha.ui.main.adapter.MainAdapter
import com.rsstudio.tamasha.ui.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(){

    var logTag = "@MainActivity"

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainAdapter: MainAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //
        initRecyclerView()
        initView()
        initObservers()
    }

    private fun initRecyclerView() {
        val llm = LinearLayoutManager(this)
        binding.rvEmployee.setHasFixedSize(true)
        binding.rvEmployee.layoutManager = llm
        mainAdapter = MainAdapter(this)
        binding.rvEmployee.adapter = mainAdapter
    }

    private fun initObservers() {

//        viewModel.employeeData.observe(this) {
//
//            if (it != null) {
//                val list: MutableList<Employee> = mutableListOf()
//                list.add(it)
//                // submit list
//                mainAdapter.submitList(list)
//                binding.iAppBar.abRoot.visibility = View.VISIBLE
//                binding.iLoader.visibility = View.GONE
//            }
//        }

        viewModel.employeesData.observe(this) {

            // submit list
            mainAdapter.submitList(it.data!!)
            binding.iLoader.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
            binding.iAppBar.abRoot.isVisible = it is Resource.Success || !it.data.isNullOrEmpty()
            binding.tvError.isVisible = it is Resource.Error && it.data.isNullOrEmpty()
            binding.tvError.text = it.error?.localizedMessage
        }

    }

    private fun initView(){

        binding.iAppBar.searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d(logTag, "onTextChanged: $s")
                mainAdapter.filter.filter(s)
            }
        })

    }

}