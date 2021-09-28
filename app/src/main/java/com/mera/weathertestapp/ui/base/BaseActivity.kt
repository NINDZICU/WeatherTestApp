package com.mera.weathertestapp.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.mera.weathertestapp.R


abstract class BaseActivity<VM : BaseViewModel>: AppCompatActivity() {
    abstract val viewModel: VM
    abstract val layoutId: Int
    abstract fun getNavController(): NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        viewModel.getErrorLiveData().observe(this) {
            Toast.makeText(this, R.string.error_occurred, Toast.LENGTH_SHORT).show()
        }
    }
}