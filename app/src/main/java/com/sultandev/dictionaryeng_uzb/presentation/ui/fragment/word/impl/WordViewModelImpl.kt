package com.sultandev.dictionaryeng_uzb.presentation.ui.fragment.word.impl

import android.database.Cursor
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import com.sultandev.dictionaryeng_uzb.domain.repository.DictionaryRepository
import com.sultandev.dictionaryeng_uzb.presentation.ui.fragment.word.WordViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WordViewModelImpl(private val dictionaryRepository: DictionaryRepository) :
    WordViewModel, ViewModel() {

    override val wordsFlow: MutableSharedFlow<Cursor> = MutableSharedFlow()
    override val clickItemFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override fun getEngUzList() {
        viewModelScope.launch(Dispatchers.IO) {
            wordsFlow.emit(dictionaryRepository.getEngUzList())
        }
    }

    override fun getFilteredList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            wordsFlow.emit(dictionaryRepository.getFilteredList(query))
        }
    }

    override fun setItemClick() {
        clickItemFlow.value = true
    }

    override fun updateDictionary(wordEntity: WordEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dictionaryRepository.updateDictionary(wordEntity)
        }
    }
}