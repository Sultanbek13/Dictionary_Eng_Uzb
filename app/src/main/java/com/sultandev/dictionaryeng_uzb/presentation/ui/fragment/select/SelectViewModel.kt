package com.sultandev.dictionaryeng_uzb.presentation.ui.fragment.select

import android.database.Cursor
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import kotlinx.coroutines.flow.SharedFlow

interface SelectViewModel {

    val allSelectedFlow: SharedFlow<Cursor>

    fun getAllSelectedList()

    fun updateDictionary(wordEntity: WordEntity)

}