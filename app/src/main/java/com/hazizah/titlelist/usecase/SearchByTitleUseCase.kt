package com.hazizah.titlelist.usecase

import com.hazizah.titlelist.domain.Article
import com.hazizah.titlelist.domain.DataRepository
import com.hazizah.titlelist.domain.Either
import com.hazizah.titlelist.domain.Failure

class SearchByTitleUseCase (
    private val repository: DataRepository
) {
    suspend fun run(search: String): Either<Failure, List<Article>> {
        val foundData = repository.findData(search)
        return if (foundData.isNullOrEmpty()) {
            Either.Left(Failure.NotFoundFailure())
        } else {
            Either.Right(foundData)
        }
    }
}