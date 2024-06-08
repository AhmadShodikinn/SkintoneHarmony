package com.example.skintoneharmony.data.retrofit

import com.example.skintoneharmony.data.response.SkintoneResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/shades?skintone=FAIR")
    fun getShades(
        @Query("q") q: String
    ): Call<SkintoneResponse>
}
//    https://skintone-be-oezdqb3ida-et.a.run.app/api/shades?skintone=FAIR