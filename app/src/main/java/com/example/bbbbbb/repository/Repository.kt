package com.example.bbbbbb.repository

import com.example.bbbbbb.model.CreatePost
import com.example.bbbbbb.model.Metrics
import com.example.bbbbbb.model.Organisation
import com.example.bbbbbb.model.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val gitHubRemoteRepository: GitHubRemoteRepository,
    private val dummyRemoteRepository: DummyRemoteRepository
) {
    suspend fun organisations(): Result<List<Organisation>?> {
        return gitHubRemoteRepository.organisations()
    }

    suspend fun metrics(username: String, password: String): Result<Metrics> {
        return gitHubRemoteRepository.metrics(username, password)
    }

    suspend fun post(createPost: CreatePost, authToken: String): Result<Post> {
        return dummyRemoteRepository.post(createPost, authToken)
    }
}
