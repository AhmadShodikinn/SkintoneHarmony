package com.example.skintoneharmony.data.retrofit

import com.example.skintoneharmony.data.response.SkintoneResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/shades/by-number")
    fun getShades(
        @Query("number") number: Int
    ): Call<List<SkintoneResponseItem>>
}
