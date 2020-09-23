package com.hazizah.titlelist.domain

interface DataRepository {
    suspend fun fetchRemoteData(): Either<Failure, List<Article>>
}