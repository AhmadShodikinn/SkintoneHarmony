package com.example.skintoneharmony

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skintoneharmony.databinding.ActivityFinalResultBinding
import com.example.skintoneharmony.databinding.ActivityFirstResultBinding

class FirstResult : AppCompatActivity() {
    private lateinit var binding: ActivityFirstResultBinding
    private lateinit var finalBinding: ActivityFinalResultBinding
    private lateinit var toneView: ImageView // Pindahkan deklarasi ini ke sini

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstResultBinding.inflate(layoutInflater)
        finalBinding = ActivityFinalResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toneView = findViewById(R.id.toneView) // Pindahkan ini ke dalam onCreate

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val result = intent.getIntExtra("skinTone", 0)
//        Toast.makeText(this, "Skin Tone: $result", Toast.LENGTH_SHORT).show()

        finalBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnBtnBtnResult.setOnClickListener {
            val intent = Intent(this, FinalResultActivity::class.java)
            intent.putExtra(FinalResultActivity.EXTRA_TONE, result)
            startActivity(intent)
        }

        setToneColor(result)
    }

    private fun setToneColor(toneResult: Int) {
        val colors = arrayOf(
            R.color.colorTone1, R.color.colorTone2, R.color.colorTone3,
            R.color.colorTone4, R.color.colorTone5, R.color.colorTone6,
            R.color.colorTone7, R.color.colorTone8, R.color.colorTone9,
            R.color.colorTone10
        )
        toneView.setBackgroundColor(ContextCompat.getColor(this, colors[toneResult - 1]))
    }
}
