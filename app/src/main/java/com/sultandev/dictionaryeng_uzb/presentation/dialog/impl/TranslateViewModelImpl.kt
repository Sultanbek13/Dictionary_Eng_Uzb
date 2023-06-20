package com.sultandev.dictionaryeng_uzb.presentation.dialog.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import com.sultandev.dictionaryeng_uzb.domain.repository.DictionaryRepository
import com.sultandev.dictionaryeng_uzb.presentation.dialog.TranslateViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class TranslateViewModelImpl(private val dictionaryRepository: DictionaryRepository) : TranslateViewModel, ViewModel() {

    override val updateDictionaryFlow: MutableSharedFlow<Unit> = MutableSharedFlow()

    override fun updateDictionary(wordEntity: WordEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dictionaryRepository.updateDictionary(wordEntity)
                updateDictionaryFlow.emit(Unit)
            } catch (_: Exception) {}
        }
    }
}