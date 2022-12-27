package com.example.kotlinretrofit

import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {
    @GET("/users")
    fun allUser() : Call<List<RetroUser?>?>?
}