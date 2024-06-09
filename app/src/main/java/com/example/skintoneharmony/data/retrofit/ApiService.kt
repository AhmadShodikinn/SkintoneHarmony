package com.example.skintoneharmony.data.retrofit

import com.example.skintoneharmony.data.response.SkintoneResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/shades")
    fun getShades(
        @Query("skintone") skintone: String
    ): Call<List<SkintoneResponseItem>>
}
