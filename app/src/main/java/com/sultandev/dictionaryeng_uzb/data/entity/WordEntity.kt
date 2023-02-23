package com.sultandev.dictionaryeng_uzb.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "dictionary")
@Parcelize
data class WordEntity(
    @PrimaryKey
    val id: Int,
    val english: String,
    val type: String,
    val transcript: String,
    val uzbek: String,
    val countable: String,
    var is_favourite: Int
): Parcelable
