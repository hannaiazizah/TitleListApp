package com.hazizah.titlelist.domain

interface DataRepository {
    suspend fun fetchRemoteData(): Either<Failure, List<Article>>
    suspend fun cacheData(data: List<Article>)
    suspend fun fetchLocalData(): List<Article>
    suspend fun findData(search: String): List<Article>
}