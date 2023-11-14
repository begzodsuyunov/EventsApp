package com.example.eventsapp.presentation.screen.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.eventsapp.R
import com.example.eventsapp.databinding.FragmentMainBinding
import com.example.eventsapp.presentation.adapters.EventAdapter
import com.example.eventsapp.presentation.viewmodels.MainViewModel
import com.example.eventsapp.presentation.viewmodels.impl.MainViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.eventsapp.service.EventService
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewBinding: FragmentMainBinding by viewBinding()

    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    private val adapter: EventAdapter by lazy {
        EventAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.listEvents.adapter = adapter

        viewModel.allEventData.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setSwitchChangedListener {
            viewModel.itemClick(it)
        }

        val intent = Intent(requireContext(), EventService::class.java)
        requireActivity().startService(intent)
    }

}