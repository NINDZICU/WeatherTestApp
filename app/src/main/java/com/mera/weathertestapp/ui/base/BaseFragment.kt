package com.mera.weathertestapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding
import com.mera.weathertestapp.R

abstract class BaseFragment<Binding : ViewBinding, VM : BaseViewModel> : Fragment() {
    abstract val viewModel: VM

    private var _binding: Binding? = null
    protected val binding get() = requireNotNull(_binding)
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    protected open fun getNavController(): NavController {
        val parentFragment = parentFragment as? BaseFragment<*, *>
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}