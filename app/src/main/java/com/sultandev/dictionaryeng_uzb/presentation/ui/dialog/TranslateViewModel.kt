package com.sultandev.dictionaryeng_uzb.presentation.ui.dialog

import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import kotlinx.coroutines.flow.SharedFlow

interface TranslateViewModel {

    val updateDictionaryFlow: SharedFlow<Unit>

    fun updateDictionary(wordEntity: WordEntity)

}