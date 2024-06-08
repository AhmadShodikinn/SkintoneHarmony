package com.example.skintoneharmony

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skintoneharmony.data.response.SkintoneResponse
import com.example.skintoneharmony.data.response.SkintoneResponseItem
import com.example.skintoneharmony.data.retrofit.ApiConfig
import com.example.skintoneharmony.databinding.ActivityFinalResultBinding
import com.example.skintoneharmony.databinding.ActivityFirstResultBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstResult : AppCompatActivity() {
    private lateinit var binding: ActivityFirstResultBinding
    private lateinit var FinalBinding: ActivityFinalResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstResultBinding.inflate(layoutInflater)
        FinalBinding = ActivityFinalResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val result = intent.getIntExtra("skinTone", 0)
        Toast.makeText(this, "Skin Tone: $result", Toast.LENGTH_SHORT).show()

        FinalBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Set onClickListeners for each button
        binding.btn1tes.setOnClickListener {
            fetchShades("FAIR")
        }

        binding.btn2tes.setOnClickListener {
            fetchShades("LIGHT")
        }

        binding.btn3tes.setOnClickListener {
            fetchShades("LIGHT MEDIUM")
        }

        binding.btn4tes.setOnClickListener {
            fetchShades("MEDIUM TAN")
        }

        binding.btn5tes.setOnClickListener {
            fetchShades("DARK")
        }
    }

    private fun fetchShades(skintone: String) {
        val client = ApiConfig.getApiService().getShades(skintone)
        client.enqueue(object : Callback<SkintoneResponse> {
            override fun onResponse(call: Call<SkintoneResponse>, response: Response<SkintoneResponse>) {
                if (response.isSuccessful) {
                    val shades = response.body()?.skintoneResponse
                    if (shades != null) {
                        FinalBinding.recyclerView.adapter = ShadeAdapter(shades)
                    }
                } else {
                    Toast.makeText(this@FirstResult, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SkintoneResponse>, t: Throwable) {
                Toast.makeText(this@FirstResult, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
