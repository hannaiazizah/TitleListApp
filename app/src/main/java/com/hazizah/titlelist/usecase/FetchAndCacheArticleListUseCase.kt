package com.hazizah.titlelist.usecase

import com.hazizah.titlelist.domain.Article
import com.hazizah.titlelist.domain.DataRepository
import com.hazizah.titlelist.domain.Either
import com.hazizah.titlelist.domain.Failure

class FetchAndCacheArticleListUseCase(
    private val repository: DataRepository
) {
    suspend fun run(): Either<Failure, List<Article>> {
        val result = repository.fetchRemoteData()
        return if (result is Either.Right) {
            Either.Right(result.right)
        } else {
            Either.Left((result as Either.Left).left)
        }
    }
}