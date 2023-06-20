package com.sultandev.dictionaryeng_uzb.presentation.fragments.word

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sultandev.dictionaryeng_uzb.R
import com.sultandev.dictionaryeng_uzb.databinding.FragmentWordBinding
import com.sultandev.dictionaryeng_uzb.presentation.adapter.DictionaryAdapter
import com.sultandev.dictionaryeng_uzb.presentation.fragments.word.impl.WordViewModelImpl
import com.sultandev.dictionaryeng_uzb.utils.updateDictionaryListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordFragment : Fragment(R.layout.fragment_word) {

    private val viewModel: WordViewModelImpl by viewModel()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) { DictionaryAdapter() }

    private val binding: FragmentWordBinding by viewBinding(FragmentWordBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listWords.adapter = adapter

        setObserver()

        btnClicks()

        viewModel.getEngUzList()

    }

    private fun setObserver() {
        viewModel.wordsFlow.onEach {
            binding.listWords.isVisible = true
            adapter.submitCursor(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.searchView.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().lowercase()
            adapter.query = query
            filterWithQuery(query)
        }

        updateDictionaryListener.observe(viewLifecycleOwner) {
            adapter.updateCursor(pos = it)
        }
    }

    private fun btnClicks() {
        adapter.setOnClickListener { data, pos ->
            try {
                findNavController().navigate(
                    WordFragmentDirections.actionWordFragmentToDialogDetail(
                        data, pos
                    )
                )
            } catch (_: IllegalArgumentException) {
            }
        }
        adapter.btnSelectClickListener { data ->
            viewModel.updateDictionary(data)
        }
    }

    private fun filterWithQuery(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(300)
            if (query.isNotEmpty()) {
                viewModel.getFilteredList(query)
            } else {
                viewModel.getEngUzList()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.searchView.setText("")
    }
}