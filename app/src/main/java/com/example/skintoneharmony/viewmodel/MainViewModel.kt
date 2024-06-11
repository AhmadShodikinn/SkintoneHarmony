package com.example.skintoneharmony.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skintoneharmony.FinalResultActivity
import com.example.skintoneharmony.ShadeAdapter
import com.example.skintoneharmony.data.response.SkintoneResponse
import com.example.skintoneharmony.data.response.SkintoneResponseItem
import com.example.skintoneharmony.data.retrofit.ApiConfig
import com.example.skintoneharmony.databinding.ActivityFinalResultBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (application: Application) : ViewModel(){
    private val _listTone = MutableLiveData<List<SkintoneResponseItem>?>()
    val listTone: MutableLiveData<List<SkintoneResponseItem>?> = _listTone

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

//    private lateinit var finalBinding: ActivityFinalResultBinding



    companion object {
        const val TAG = "FinalResultActivity"
    }

//    init{
//        fetchShades("FAIR")
//    }

//    fun fetchShades(skintone: String) {
//        try {
//            val client = ApiConfig.getApiService().getShades(skintone)
//            client.enqueue(object : Callback<SkintoneResponse> {
//                override fun onResponse(call: Call<SkintoneResponse>, response: Response<SkintoneResponse>) {
//                    if (response.isSuccessful) {
//                        _listTone.value = response.body()?.skintoneResponse
//                    }
//                }
//                override fun onFailure(call: Call<SkintoneResponse>, t: Throwable) {
//                    Log.e(TAG, "Failed to retrieve data", t)
//                }
//            })
//        }catch (e: Exception){
//            Log.d("Token e", e.toString())
//        }
//
//    }

    fun findShades(skintone: Int) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getShades(skintone)
        client.enqueue(object : Callback<List<SkintoneResponseItem>> {
            override fun onResponse(
                call: Call<List<SkintoneResponseItem>>,
                response: Response<List<SkintoneResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listTone.value = responseBody
//                        val skintones = responseBody.map { it.skintone }
                    } else {
                        Log.e(TAG, "Response Body is null")
//                        Toast.makeText(this@FinalResultActivity, "Response Body is null", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e(TAG, "Error Response: ${response.message()}")
                    Log.e(TAG, "Error Code: ${response.code()}")
                    Log.e(TAG, "Error Body: ${response.errorBody()?.string()}")
//                    Toast.makeText(this@FinalResultActivity, "Error Response: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<SkintoneResponseItem>>, t: Throwable) {
                _isLoading.value = false

                Log.e(TAG, "onFailure: ${t.message}")
//                Toast.makeText(this@FinalResultActivity, "Request failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

//    private fun setShadeData(consumerReviews: List<SkintoneResponseItem>) {
//        adapter.submitList(consumerReviews)
//    }
}
