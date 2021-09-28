package com.mera.weathertestapp.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.mera.weathertestapp.R

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {
    abstract val viewModel: VM

    protected open fun getNavController(): NavController {
        val parentFragment = parentFragment as? BaseFragment<*>
        val parentActivity = activity as? BaseActivity<*>
        val controller = parentFragment?.getNavController() ?: parentActivity?.getNavController()
        return checkNotNull(controller) { "Controller not found" }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getErrorLiveData().observe {
            Toast.makeText(requireContext(), R.string.error_occurred, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun <T> LiveData<T>.observe(action: (T) -> Unit) {
        observe(viewLifecycleOwner, action)
    }
}