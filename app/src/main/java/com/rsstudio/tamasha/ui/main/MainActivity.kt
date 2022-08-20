package com.rsstudio.tamasha.ui.main


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rsstudio.tamasha.R
import com.rsstudio.tamasha.databinding.ActivityMainBinding
import com.rsstudio.tamasha.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


}