package com.carlosscotus.wecasts.presentation.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlosscotus.wecasts.R
import com.carlosscotus.wecasts.databinding.HomeFragmentBinding
import com.carlosscotus.wecasts.presentation.MainViewModel
import com.carlosscotus.wecasts.presentation.adapters.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
        setupViewModel()
    }

    private fun setupRecyclerview() {
        with(binding.recyclerview) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            val divider = DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )

            addItemDecoration(divider)
        }
    }

    private fun setupViewModel() {
        viewModel.podcastsLiveData.observe(viewLifecycleOwner) {
            val response = it.toMutableList()
            binding.recyclerview.adapter = MainAdapter(response, requireActivity())
        }

        viewModel.getPodcasts()
    }

    companion object {
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }
}