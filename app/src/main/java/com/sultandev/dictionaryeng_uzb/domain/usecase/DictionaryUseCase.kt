package com.sultandev.dictionaryeng_uzb.domain.usecase

import android.database.Cursor
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity

interface DictionaryUseCase {

    fun getEngUzList(): Cursor

    fun getFilteredList(query: String): Cursor

    fun updateDictionary(wordEntity: WordEntity)

    fun getWordSelect(): Cursor

}