package com.example.skintoneharmony

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skintoneharmony.data.response.SkintoneResponseItem
import com.example.skintoneharmony.data.retrofit.ApiConfig
import com.example.skintoneharmony.databinding.ActivityFinalResultBinding
import com.example.skintoneharmony.viewmodel.MainViewModel
import com.example.skintoneharmony.viewmodel.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinalResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinalResultBinding
    private lateinit var adapter: ShadeAdapter

    val factory: ViewModelFactory by lazy {
        ViewModelFactory.getInstance(application)
    }
    val mainViewModel: MainViewModel by viewModels {
        factory
    }
//    companion object {
//        const val TAG = "FinalResultActivity"
//        const val SKINTONE = "FAIR"
//    }

    companion object {
        const val EXTRA_TONE = "extra_tone"
        const val EXTRA_TONE1 = "extra_tone1"
        var selectedTone: Int= 0

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.listTone.observe(this) { listTone ->
            if (listTone != null) {
                setShadeData(listTone)
            }
        }
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        selectedTone = intent.getIntExtra(EXTRA_TONE1, 0)

        mainViewModel.findShades(selectedTone)

        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
//        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
//        binding.recyclerView.addItemDecoration(itemDecoration)

        // Initialize the adapter and set it to the RecyclerView
        adapter = ShadeAdapter()
        binding.recyclerView.adapter = adapter


//        findShades()
        binding.btnStartOver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val textView4 = findViewById<TextView>(R.id.textView4)
        val colors = listOf(
            R.color.colorTone1, R.color.colorTone2, R.color.colorTone3,
            R.color.colorTone4, R.color.colorTone5, R.color.colorTone6,
            R.color.colorTone7, R.color.colorTone8, R.color.colorTone9,
            R.color.colorTone10
        )
        val backgroundColor = colors[selectedTone.coerceIn(1, 10) - 1]
        textView4.setBackgroundResource(backgroundColor)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_setting -> {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

//    private fun findShades() {
//        val client = ApiConfig.getApiService().getShades(SKINTONE)
//        client.enqueue(object : Callback<List<SkintoneResponseItem>> {
//            override fun onResponse(
//                call: Call<List<SkintoneResponseItem>>,
//                response: Response<List<SkintoneResponseItem>>
//            ) {
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        setShadeData(responseBody)
//                    } else {
//                        Log.e(TAG, "Response Body is null")
//                        Toast.makeText(this@FinalResultActivity, "Response Body is null", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Log.e(TAG, "Error Response: ${response.message()}")
//                    Log.e(TAG, "Error Code: ${response.code()}")
//                    Log.e(TAG, "Error Body: ${response.errorBody()?.string()}")
//                    Toast.makeText(this@FinalResultActivity, "Error Response: ${response.message()}", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<SkintoneResponseItem>>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message}")
//                Toast.makeText(this@FinalResultActivity, "Request failed: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//
    private fun setShadeData(listTone: List<SkintoneResponseItem>) {
        adapter.submitList(listTone)
    }


}
