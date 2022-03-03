package com.example.bbbbbb.repository

import com.example.bbbbbb.model.Metrics
import com.example.bbbbbb.model.Organisation
import com.example.bbbbbb.service.GithubService
import retrofit2.awaitResponse
import javax.inject.Inject

class GitHubRemoteRepository @Inject constructor(
    private val loginService: GithubService,
) {
    suspend fun organisations(): Result<List<Organisation>?> {
        return try {
            val response = loginService.organisations()?.awaitResponse()
            if (response?.isSuccessful == true) {
                Result.success(response.body())
            } else {
                Result.failure(Throwable("Error getting organisations, response code: ${response?.code()}"))
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    suspend fun metrics(username: String, password: String): Result<Metrics> {
        return try {
            val response =
                loginService.repositoryProfileMetrics(username, password)?.awaitResponse()
            if (response?.isSuccessful == true && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Throwable("Error getting metrics, response code: ${response?.code()}"))
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

}