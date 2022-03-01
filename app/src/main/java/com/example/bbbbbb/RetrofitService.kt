package com.example.bbbbbb

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.*


interface RetrofitService {

    @GET("/repos/{owner}/{repo}/community/profile")
    fun repositoryProfileMetrics(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<Metrics?>?

    @GET("/organizations")
    fun organisations(): Call<List<Organisation>?>?

    @FormUrlEncoded
    @POST("/api/users?")
    fun doCreateUserWithField(
        @Field("name") name: String?,
        @Field("job") job: String?
    ): Call<UserList?>?

}

interface DummyService {

    @POST("post/create")

    fun createPost(
        @Body owner: CreatePost,
        @Header("app-id") authToken: String
    ): Call<Post?>?
}

@Serializable
data class Metrics(
    @SerialName("health_percentage")
    val healthPercentage: Int?,
    val description: String?,
    val documentation: String?
)

@Serializable
data class User(
    val id: Int,
    val name: String,
    val job: String
)

@Serializable
data class UserList(val users: List<User>)

@Serializable
data class Organisation(
    val login: String,
    val id: Int,
    val description: String?
)

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
