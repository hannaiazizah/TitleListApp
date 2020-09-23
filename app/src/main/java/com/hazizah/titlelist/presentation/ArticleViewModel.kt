package com.hazizah.titlelist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazizah.titlelist.domain.Article
import com.hazizah.titlelist.domain.Either
import com.hazizah.titlelist.domain.Resource
import com.hazizah.titlelist.usecase.FetchAndCacheArticleListUseCase
import com.hazizah.titlelist.usecase.SearchByTitleUseCase
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val fetchUseCase: FetchAndCacheArticleListUseCase,
    private val searchUseCase: SearchByTitleUseCase
): ViewModel() {

    private val _articleList = MutableLiveData<Resource<List<Article>>>()
    val articleList: LiveData<Resource<List<Article>>> = _articleList

    fun getData() {
        _articleList.postValue(Resource.loading())
        viewModelScope.launch {
            val result = fetchUseCase.run()
            if (result is Either.Right) {
                _articleList.postValue(Resource.success(result.right))
            } else {
                _articleList.postValue(Resource.error((result as Either.Left).left))
            }
        }
    }

    fun search(keyword: String) {
        _articleList.postValue(Resource.loading())
        viewModelScope.launch {
            val result = searchUseCase.run(keyword)
            if (result is Either.Right) {
                _articleList.postValue(Resource.success(result.right))
            } else {
                _articleList.postValue(Resource.error((result as Either.Left).left))
            }
        }
    }

}