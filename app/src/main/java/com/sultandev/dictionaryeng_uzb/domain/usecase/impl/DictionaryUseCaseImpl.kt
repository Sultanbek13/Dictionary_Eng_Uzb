package com.sultandev.dictionaryeng_uzb.domain.usecase.impl

import android.database.Cursor
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import com.sultandev.dictionaryeng_uzb.domain.repository.DictionaryRepository
import com.sultandev.dictionaryeng_uzb.domain.usecase.DictionaryUseCase

class DictionaryUseCaseImpl(private val dictionaryRepository: DictionaryRepository) : DictionaryUseCase {
    override fun getEngUzList(): Cursor = dictionaryRepository.getEngUzList()

    override fun getFilteredList(query: String): Cursor = dictionaryRepository.getFilteredList(query)

    override fun updateDictionary(wordEntity: WordEntity) = dictionaryRepository.updateDictionary(wordEntity)

    override fun getWordSelect(): Cursor = dictionaryRepository.getWordSelect()
}