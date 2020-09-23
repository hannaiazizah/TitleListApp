package com.hazizah.titlelist.data.remote

import com.hazizah.titlelist.data.local.AppDatabase
import com.hazizah.titlelist.domain.Article
import com.hazizah.titlelist.domain.DataRepository
import com.hazizah.titlelist.domain.Either
import com.hazizah.titlelist.domain.Failure
import retrofit2.HttpException
import java.lang.Exception

class DataRepositoryImpl(
    private val client: RetrofitClient,
    private val appDatabase: AppDatabase
): DataRepository {
    override suspend fun fetchRemoteData(): Either<Failure, List<Article>> {
        return try {
            val result = client.fetchData()
            return Either.Right(result)
        } catch (e: Exception) {
            when (e) {
                is HttpException -> Either.Left(Failure.ServerFailure())
                else -> Either.Left(Failure.UnknownFailure())
            }
        }
    }
}