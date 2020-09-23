package com.hazizah.titlelist.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class Article(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)