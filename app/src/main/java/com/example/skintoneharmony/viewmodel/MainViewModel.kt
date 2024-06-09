package com.example.skintoneharmony.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skintoneharmony.data.response.SkintoneResponse
import com.example.skintoneharmony.data.response.SkintoneResponseItem
import com.example.skintoneharmony.data.retrofit.ApiConfig
import com.example.skintoneharmony.databinding.ActivityFinalResultBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (application: Application) : ViewModel(){
    private val _listTone = MutableLiveData<List<SkintoneResponseItem>>()
    private lateinit var finalBinding: ActivityFinalResultBinding

    val listTone: LiveData<List<SkintoneResponseItem>> = _listTone


    companion object{
        private const val TAG = "MainViewModel"
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
}
