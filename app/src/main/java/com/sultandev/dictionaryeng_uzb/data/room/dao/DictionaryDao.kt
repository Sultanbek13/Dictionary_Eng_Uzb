package com.sultandev.dictionaryeng_uzb.data.room.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {

    @Query("SELECT * FROM dictionary")
    fun getEngUzList(): Cursor

    @Query("SELECT * FROM dictionary WHERE english LIKE '%' || :query || '%'")
    fun getFilteredList(query: String): Cursor

    @Update
    fun updateDictionary(wordEntity: WordEntity)

    @Query("SELECT * FROM dictionary WHERE is_favourite = 1")
    fun getWordSelect(): Cursor

}