package com.sultandev.dictionaryeng_uzb.presentation.ui.fragment.select.impl

import android.database.Cursor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import com.sultandev.dictionaryeng_uzb.domain.repository.DictionaryRepository
import com.sultandev.dictionaryeng_uzb.presentation.ui.fragment.select.SelectViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SelectVIewModelImpl(private val dictionaryRepository: DictionaryRepository): SelectViewModel, ViewModel() {

    override val allSelectedFlow: MutableSharedFlow<Cursor> = MutableSharedFlow()

    override fun getAllSelectedList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                allSelectedFlow.emit(dictionaryRepository.getWordSelect())
            } catch (_: Exception) {}
        }
    }

    override fun updateDictionary(wordEntity: WordEntity) {
        dictionaryRepository.updateDictionary(wordEntity)
        getAllSelectedList()
    }
}