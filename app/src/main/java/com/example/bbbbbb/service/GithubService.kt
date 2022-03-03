package com.example.bbbbbb.service

import com.example.bbbbbb.model.Metrics
import com.example.bbbbbb.model.Organisation
import retrofit2.Call
import retrofit2.http.*


interface GithubService {

    @GET(COMMUNITY_PROFILE_STATISTICS)
    fun repositoryProfileMetrics(
        @Path(OWNER) owner: String,
        @Path(REPO) repo: String
    ): Call<Metrics?>?

    @GET(ORGANIZATIONS)
    fun organisations(): Call<List<Organisation>?>?

    companion object {
        private const val COMMUNITY_PROFILE_STATISTICS = "/repos/{owner}/{repo}/community/profile"
        private const val OWNER = "owner"
        private const val REPO = "repo"
        private const val ORGANIZATIONS = "/organizations"
    }
}
