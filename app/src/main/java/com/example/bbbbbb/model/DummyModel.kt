package com.example.bbbbbb.model

import kotlinx.serialization.Serializable


@Serializable
data class CreatePost(
    val text: String,
    val image: String,
    val likes: Int,
    val tags: List<String>,
    val owner: String
)

@Serializable
data class Post(
    val id: String
)
