package com.mera.weathertestapp.ui.main

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mera.weathertestapp.R
import com.mera.weathertestapp.ui.base.BaseActivity
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModel: MainViewModel by inject<MainViewModelImpl>()

    override val layoutId: Int = R.layout.activity_main

    override fun getNavController(): NavController {
        return Navigation.findNavController(this, R.id.nav_host_fragment)
    }

}