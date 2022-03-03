package com.example.bbbbbb.repository

import com.example.bbbbbb.service.DummyService
import com.example.bbbbbb.model.CreatePost
import com.example.bbbbbb.model.Post
import retrofit2.awaitResponse
import javax.inject.Inject

class DummyRemoteRepository @Inject constructor(
    private val dummyService: DummyService
) {
    suspend fun post(createPost: CreatePost, authToken: String): Result<Post> {
        return try {
            val response = dummyService.createPost(createPost, authToken)?.awaitResponse()
            if (response?.isSuccessful == true && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Throwable("Error creating post, response code: ${response?.code()}"))
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}