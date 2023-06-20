package com.sultandev.dictionaryeng_uzb.presentation.fragments.select.impl

import android.database.Cursor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import com.sultandev.dictionaryeng_uzb.domain.repository.DictionaryRepository
import com.sultandev.dictionaryeng_uzb.domain.usecase.DictionaryUseCase
import com.sultandev.dictionaryeng_uzb.presentation.fragments.select.SelectViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SelectVIewModelImpl(private val dictionaryUseCase: DictionaryUseCase): SelectViewModel, ViewModel() {

    override val allSelectedFlow: MutableSharedFlow<Cursor> = MutableSharedFlow()

    override fun getAllSelectedList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                allSelectedFlow.emit(dictionaryUseCase.getWordSelect())
            } catch (_: Exception) {}
        }
    }

    override fun updateDictionary(wordEntity: WordEntity) {
        dictionaryUseCase.updateDictionary(wordEntity)
        getAllSelectedList()
    }
}