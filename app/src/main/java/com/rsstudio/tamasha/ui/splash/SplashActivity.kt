package com.rsstudio.tamasha.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.rsstudio.tamasha.R
import com.rsstudio.tamasha.databinding.ActivitySplashBinding
import com.rsstudio.tamasha.ui.base.BaseActivity
import com.rsstudio.tamasha.ui.main.MainActivity

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        //
        gotoMainActivity()
    }

    private fun gotoMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed(
            Runnable {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 500
        )
    }
}