package com.sultandev.dictionaryeng_uzb.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import com.sultandev.dictionaryeng_uzb.data.room.dao.DictionaryDao

@Database(entities = [WordEntity::class], version = 11, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

}