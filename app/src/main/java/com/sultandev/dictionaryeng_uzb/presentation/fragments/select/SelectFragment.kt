package com.sultandev.dictionaryeng_uzb.presentation.fragments.select

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sultandev.dictionaryeng_uzb.R
import com.sultandev.dictionaryeng_uzb.databinding.FragmentSelectBinding
import com.sultandev.dictionaryeng_uzb.presentation.adapter.DictionaryAdapter
import com.sultandev.dictionaryeng_uzb.presentation.fragments.select.impl.SelectVIewModelImpl
import com.sultandev.dictionaryeng_uzb.utils.updateDictionaryListener
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectFragment: Fragment(R.layout.fragment_select) {

    private val binding: FragmentSelectBinding by viewBinding()
    private val viewModel: SelectVIewModelImpl by viewModel()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { DictionaryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listSelected.adapter = adapter

        setObservers()

        btnClicks()

        viewModel.getAllSelectedList()

    }

    private fun setObservers() {
        viewModel.allSelectedFlow.onEach {
            if (it.count == 0) {
                binding.llNoSelected.isVisible = true
                binding.listSelected.isVisible = false
            } else {
                binding.llNoSelected.isVisible = false
                binding.listSelected.isVisible = true
                adapter.submitCursor(it)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        updateDictionaryListener.observe(viewLifecycleOwner) {
            viewModel.getAllSelectedList()
        }
    }

    private fun btnClicks() {
        adapter.setOnClickListener { data, pos ->
            try {
                findNavController().navigate(
                    SelectFragmentDirections.actionSelectFragmentToDialogDetail(
                        data, pos
                    )
                )
            } catch (_: IllegalArgumentException) {}
        }
        adapter.btnSelectClickListener { data ->
            viewModel.updateDictionary(data)
        }
    }
}