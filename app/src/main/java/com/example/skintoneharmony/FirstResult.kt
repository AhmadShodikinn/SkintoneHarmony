package com.example.skintoneharmony

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skintoneharmony.data.response.SkintoneResponse
import com.example.skintoneharmony.data.response.SkintoneResponseItem
import com.example.skintoneharmony.data.retrofit.ApiConfig
import com.example.skintoneharmony.databinding.ActivityFinalResultBinding
import com.example.skintoneharmony.databinding.ActivityFirstResultBinding
import com.example.skintoneharmony.viewmodel.MainViewModel
import com.example.skintoneharmony.viewmodel.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstResult : AppCompatActivity() {
    private lateinit var binding: ActivityFirstResultBinding
    private lateinit var finalBinding: ActivityFinalResultBinding

//    val factory: ViewModelFactory by lazy {
//        ViewModelFactory.getInstance(application)
//    }
//
//    val viewModel: MainViewModel by viewModels {
//        factory
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstResultBinding.inflate(layoutInflater)
        finalBinding = ActivityFinalResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val result = intent.getIntExtra("skinTone", 0)
        Toast.makeText(this, "Skin Tone: $result", Toast.LENGTH_SHORT).show()


        finalBinding.recyclerView.layoutManager = LinearLayoutManager(this)

//        // Set onClickListeners for each button
//        binding.btn1tes.setOnClickListener {
//            val intent = Intent(this, FinalResultActivity::class.java)
//            intent.putExtra(FinalResultActivity.EXTRA_TONE, "FAIR")
//            startActivity(intent)
//        }

        binding.btnBtnBtnResult.setOnClickListener {
            val intent = Intent(this, FinalResultActivity::class.java)
            intent.putExtra(FinalResultActivity.EXTRA_TONE1, result)
            startActivity(intent)
        }

//        binding.btn2tes.setOnClickListener {
////            viewModel.findShades("LIGHT")
//            val intent = Intent(this, FinalResultActivity::class.java)
//            intent.putExtra(FinalResultActivity.EXTRA_TONE, "DARK")
//            startActivity(intent)
//        }
        val toneView: ImageView = findViewById(R.id.toneView)
        fun setToneColor(toneResult: Int) {
            when (toneResult) {
                1 -> toneView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTone1))
                2 -> toneView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTone2))
                3 -> toneView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTone3))
                4 -> toneView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTone4))
                5 -> toneView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTone5))
                6 -> toneView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTone6))
                7 -> toneView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTone7))
                8 -> toneView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTone8))
                9 -> toneView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTone9))
                10 -> toneView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTone10))
            }
        }

        setToneColor(result)

//
//
//        binding.btn2tes.setOnClickListener {
//            fetchShades("LIGHT")
//        }
//
//        binding.btn3tes.setOnClickListener {
//            fetchShades("LIGHT MEDIUM")
//        }
//
//        binding.btn4tes.setOnClickListener {
//            fetchShades("MEDIUM TAN")
//        }
//
//        binding.btn5tes.setOnClickListener {
//            fetchShades("DARK")
//        }
    }

//    private fun setShade(listTone:List<SkintoneResponseItem>){
//        val adapter = ShadeAdapter()
//        adapter.submitList(listUsers)
//        binding.rvUsers.adapter = adapter
//        adapter.setOnItemClickCallback(object : UsersAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: ItemsItem) {
//                selectedUser(data)
//            }
//        })
//    }

}