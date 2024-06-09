package com.example.skintoneharmony

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

    val factory: ViewModelFactory by lazy {
        ViewModelFactory.getInstance(application)
    }

    val viewModel: MainViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstResultBinding.inflate(layoutInflater)
        finalBinding = ActivityFinalResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        val result = intent.getIntExtra("skinTone", 0)
        Toast.makeText(this, "Skin Tone: $result", Toast.LENGTH_SHORT).show()

        finalBinding.recyclerView.layoutManager = LinearLayoutManager(this)

//        // Set onClickListeners for each button
        binding.btn1tes.setOnClickListener {
//            fetchShades("FAIR")
        }

        binding.btnBtnBtnResult.setOnClickListener {
            val intent = Intent(this, FinalResultActivity::class.java)
            startActivity(intent)
        }

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



