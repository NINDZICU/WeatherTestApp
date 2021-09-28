package com.mera.weathertestapp.ui.main.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mera.weathertestapp.databinding.SearchFragmentBinding
import com.mera.weathertestapp.domain.entity.CityEntity
import com.mera.weathertestapp.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SearchFragment: BaseFragment<SearchViewModel>() {
    override val viewModel: SearchViewModel by inject<SearchViewModelImpl> {
        parametersOf(getNavController())
    }

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val selectResultListener = object : SearchResultsAdapter.SearchResultsListener {
        override fun onSelectResult(city: CityEntity) {
            viewModel.onSelectResult(city)
        }
    }

    private val resultsAdapter = SearchResultsAdapter(selectResultListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            closeBtn.setOnClickListener {
                getNavController().popBackStack()
            }

            searchResults.adapter = resultsAdapter
            searchResults.layoutManager = LinearLayoutManager(context)

            cityEt.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == KeyEvent.KEYCODE_ENTER || actionId == KeyEvent.KEYCODE_ENDCALL
                    || actionId == KeyEvent.KEYCODE_CALL) {
                    viewModel.onSearch(textView.text.toString())
                }
                false
            }

            viewModel.results().observe {
                resultsAdapter.setCities(it)
            }

            viewModel.isProgress().observe {
                if (it) {
                    cityEt.visibility = View.INVISIBLE
                    cityProgress.visibility = View.VISIBLE
                } else {
                    cityProgress.visibility = View.GONE
                    cityEt.visibility = View.VISIBLE
                }
            }

            viewModel.isEmptyData().observe {
                if(it) emptyData.visibility = View.VISIBLE
                else emptyData.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}