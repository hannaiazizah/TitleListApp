package com.hazizah.titlelist.di

import com.hazizah.titlelist.data.local.AppDatabase
import com.hazizah.titlelist.data.remote.DataRepositoryImpl
import com.hazizah.titlelist.data.remote.RetrofitClient
import com.hazizah.titlelist.domain.DataRepository
import com.hazizah.titlelist.presentation.ArticleViewModel
import com.hazizah.titlelist.usecase.FetchAndCacheArticleListUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<DataRepository> {
        DataRepositoryImpl(
            client = RetrofitClient(),
            appDatabase = AppDatabase.getDatabase(get())
        )
    }

    factory {
        FetchAndCacheArticleListUseCase(
            repository = get()
        )
    }

    viewModel {
        ArticleViewModel(
            useCase = get()
        )
    }
}