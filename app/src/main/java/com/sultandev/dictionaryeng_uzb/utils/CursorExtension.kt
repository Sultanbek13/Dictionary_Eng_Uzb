package com.sultandev.dictionaryeng_uzb.utils

import android.database.Cursor
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity

fun Cursor.geWordData(): WordEntity {
    return WordEntity(
        getInt(0),
        getString(1),
        getString(2),
        getString(3),
        getString(4),
        getString(5),
        getInt(6),
    )
}