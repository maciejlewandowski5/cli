package com.example.bbbbbb.service

import com.example.bbbbbb.model.CreatePost
import com.example.bbbbbb.model.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface DummyService {

    @POST(CREATE_POST)
    fun createPost(@Body owner: CreatePost, @Header(APP_ID) authToken: String): Call<Post?>?

    companion object {
        private const val CREATE_POST = "post/create"
        private const val APP_ID = "app-id"
    }
}
