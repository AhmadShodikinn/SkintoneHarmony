package com.example.skintoneharmony

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.skintoneharmony.databinding.ActivityFirstResultBinding

class FirstResult : AppCompatActivity() {
    private lateinit var binding: ActivityFirstResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //start code in here
        val result = intent.getIntExtra("skinTone", 0)
        // add retrofit model, call image, show on this binding
        Toast.makeText(this, "Skin Tone: $result", Toast.LENGTH_SHORT).show()

        binding.btnBtnBtnResult.setOnClickListener {
            val intent = Intent(this, FinalResultActivity::class.java)
            Log.d("FirstResult", "Button clicked, starting SecondResult Activity")
            startActivity(intent)
        }
    }

}