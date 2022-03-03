package com.example.bbbbbb

import com.example.bbbbbb.service.DummyService
import com.example.bbbbbb.service.GithubService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideLoginRetrofitService(): GithubService {
        return Retrofit.Builder()
            .baseUrl(GITHUB_URL)
            .addConverterFactory(JSON_FACTORY)
            .build()
            .create(GithubService::class.java)
    }

    @Singleton
    @Provides
    fun provideDummyService(): DummyService {
        return Retrofit.Builder()
            .baseUrl(DUMMY_URL)
            .addConverterFactory(JSON_FACTORY)
            .build()
            .create(DummyService::class.java)
    }

    companion object {
        private const val GITHUB_URL = "https://api.github.com"
        private const val DUMMY_URL = "https://dummyapi.io/data/v1/"
        private val APPLICATION_JSON = "application/json".toMediaType()
        private val JSON = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        private val JSON_FACTORY = JSON.asConverterFactory(APPLICATION_JSON)

    }
}
