package com.hazizah.titlelist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hazizah.titlelist.domain.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articleList: List<Article>)

    @Query("SELECT * FROM article")
    suspend fun getAll(): List<Article>

    @Query("DELETE FROM article")
    suspend fun deleteAll()

    @Query("SELECT * FROM article WHERE title LIKE '%' || :search || '%'")
    suspend fun findTitle(search: String): List<Article>

}