package com.sultandev.dictionaryeng_uzb.domain.repository

import android.database.Cursor
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {

    fun getEngUzList(): Cursor

    fun getFilteredList(query: String): Cursor

    fun updateDictionary(wordEntity: WordEntity)

    fun getWordSelect(): Cursor

}