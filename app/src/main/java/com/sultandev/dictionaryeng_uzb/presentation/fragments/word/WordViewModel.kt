package com.sultandev.dictionaryeng_uzb.presentation.fragments.word

import android.database.Cursor
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface WordViewModel {

    val wordsFlow: SharedFlow<Cursor>

    val clickItemFlow: Flow<Boolean>

    fun getEngUzList()

    fun getFilteredList(query: String)

    fun setItemClick()

    fun updateDictionary(wordEntity: WordEntity)

}