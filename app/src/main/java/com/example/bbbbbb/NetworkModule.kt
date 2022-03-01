package com.example.bbbbbb

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true}

    @Singleton
    @Provides
    fun provideLoginRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RetrofitService::class.java)
    }
    @Singleton
    @Provides
    fun provideDummyService(): DummyService {
        return Retrofit.Builder()
            .baseUrl("https://dummyapi.io/data/v1/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(DummyService::class.java)
    }
}