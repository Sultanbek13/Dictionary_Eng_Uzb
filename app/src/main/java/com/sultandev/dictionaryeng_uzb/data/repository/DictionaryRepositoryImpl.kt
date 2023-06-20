package com.sultandev.dictionaryeng_uzb.data.repository

import android.database.Cursor
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import com.sultandev.dictionaryeng_uzb.domain.repository.DictionaryRepository
import com.sultandev.dictionaryeng_uzb.data.room.dao.DictionaryDao
import kotlinx.coroutines.flow.Flow

class DictionaryRepositoryImpl(private val dictionaryDao: DictionaryDao) : DictionaryRepository {

    override fun getEngUzList(): Cursor = dictionaryDao.getEngUzList()

    override fun getFilteredList(query: String): Cursor =
        dictionaryDao.getFilteredList(query)

    override fun updateDictionary(wordEntity: WordEntity) =
        dictionaryDao.updateDictionary(wordEntity)

    override fun getWordSelect(): Cursor = dictionaryDao.getWordSelect()

}