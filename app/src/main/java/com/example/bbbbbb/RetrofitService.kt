package com.example.bbbbbb

import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.*


interface RetrofitService {

    @POST("/api/users")
    fun createUser(@Body user: User?): Call<User?>?

    @GET("/api/users?")
    fun doGetUserList(@Query("page") page: String?): Call<UserList?>?

    @FormUrlEncoded
    @POST("/api/users?")
    fun doCreateUserWithField(
        @Field("name") name: String?,
        @Field("job") job: String?
    ): Call<UserList?>?

}

@Serializable
data class User(
    val id: Int,
    val name: String,
    val job: String
)

@Serializable
data class UserList(val users: List<User>)