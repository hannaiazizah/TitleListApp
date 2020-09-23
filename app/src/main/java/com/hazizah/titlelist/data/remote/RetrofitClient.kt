package com.hazizah.titlelist.data.remote

import com.google.gson.GsonBuilder
import com.hazizah.titlelist.domain.Article
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        const val API_URL = "https://jsonplaceholder.typicode.com/"
    }

    private lateinit var client: ApiService

    private val okHttpClient = OkHttpClient
        .Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    init {
        createApiService()
    }

    private fun createApiService() {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        client = retrofit.create(ApiService::class.java)
    }

    suspend fun fetchData(): List<Article> {
        return client.retrieveArticles()
    }

}