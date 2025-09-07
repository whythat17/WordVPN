package com.wordvpn.app.net

import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("health")
    fun health(): Call<String>
}
