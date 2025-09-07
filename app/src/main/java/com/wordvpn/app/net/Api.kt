package com.wordvpn.app.net

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

// Только интерфейс и синглтон! Никаких data class здесь.
interface BackendService {
    @POST("/api/validate")
    suspend fun validateCode(@Body body: VerifyRequest): Response<Void>
}

object Api {
    // Поменяй на адрес твоего сервера
    private const val BASE_URL = "http://192.168.1.20:8080"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: BackendService by lazy {
        retrofit.create(BackendService::class.java)
    }
}
