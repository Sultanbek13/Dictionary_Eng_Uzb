package com.sultandev.dictionaryeng_uzb.di

import android.content.Context
import androidx.room.Room
import com.sultandev.dictionaryeng_uzb.domain.repository.DictionaryRepository
import com.sultandev.dictionaryeng_uzb.data.repository.DictionaryRepositoryImpl
import com.sultandev.dictionaryeng_uzb.data.room.AppDatabase
import com.sultandev.dictionaryeng_uzb.data.room.dao.DictionaryDao
import com.sultandev.dictionaryeng_uzb.domain.usecase.DictionaryUseCase
import com.sultandev.dictionaryeng_uzb.domain.usecase.impl.DictionaryUseCaseImpl
import com.sultandev.dictionaryeng_uzb.presentation.dialog.impl.TranslateViewModelImpl
import com.sultandev.dictionaryeng_uzb.presentation.fragments.select.impl.SelectVIewModelImpl
import com.sultandev.dictionaryeng_uzb.presentation.fragments.word.impl.WordViewModelImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

    fun provideRoomDatabase(ctx: Context): AppDatabase {
        return Room
            .databaseBuilder(ctx.applicationContext, AppDatabase::class.java, "dictionary1.db")
            .createFromAsset("dictionary1.db")
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: AppDatabase): DictionaryDao = database.dictionaryDao()

    single { provideRoomDatabase(androidContext()) }
    single { provideDao(get()) }
    single<DictionaryRepository> { DictionaryRepositoryImpl(get()) }
}

val domainModule = module {
    single<DictionaryUseCase> { DictionaryUseCaseImpl(get()) }
}

val viewModelModule = module {
    viewModel { WordViewModelImpl(get()) }
    viewModel { TranslateViewModelImpl(get()) }
    viewModel { SelectVIewModelImpl(get()) }
}