package com.hazizah.titlelist.data.remote

import com.hazizah.titlelist.domain.Article
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun retrieveArticles(): List<Article>
}