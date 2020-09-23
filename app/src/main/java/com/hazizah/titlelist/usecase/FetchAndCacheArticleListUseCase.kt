package com.hazizah.titlelist.usecase

import com.hazizah.titlelist.domain.Article
import com.hazizah.titlelist.domain.DataRepository
import com.hazizah.titlelist.domain.Either
import com.hazizah.titlelist.domain.Failure

class FetchAndCacheArticleListUseCase(
    private val repository: DataRepository
) {
    suspend fun run(): Either<Failure, List<Article>> {
        val remoteData = repository.fetchRemoteData()
        return if (remoteData is Either.Right) {
            // store data to local db
            repository.cacheData(remoteData.right)
            Either.Right(remoteData.right)
        } else {
            // get local data
            val localData = repository.fetchLocalData()
            if (!localData.isNullOrEmpty()) {
                Either.Right(localData)
            } else {
                Either.Left((remoteData as Either.Left).left)
            }
        }
    }
}