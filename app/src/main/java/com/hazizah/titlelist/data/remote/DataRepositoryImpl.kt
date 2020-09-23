package com.hazizah.titlelist.data.remote

import com.hazizah.titlelist.domain.Article
import com.hazizah.titlelist.domain.DataRepository
import com.hazizah.titlelist.domain.Either
import com.hazizah.titlelist.domain.Failure

class DataRepositoryImpl: DataRepository {
    override suspend fun fetchRemoteData(): Either<Failure, List<Article>> {
        TODO("Not yet implemented")
    }
}