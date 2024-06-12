package com.example.skintoneharmony

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skintoneharmony.data.response.SkintoneResponseItem
import com.example.skintoneharmony.databinding.ActivityFinalResultBinding
import com.example.skintoneharmony.databinding.ShadeListBinding
import com.example.skintoneharmony.viewmodel.MainViewModel
import com.example.skintoneharmony.viewmodel.ViewModelFactory

class FinalResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinalResultBinding
    private lateinit var listBinding: ShadeListBinding
    private lateinit var adapter: ShadeAdapter

    private val factory: ViewModelFactory by lazy {
        ViewModelFactory.getInstance(application)
    }

    private val mainViewModel: MainViewModel by viewModels {
        factory
    }

    companion object {
//        const val EXTRA_TONE = "extra_tone"
        const val EXTRA_TONE = "extra_tone"
        var selectedTone: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalResultBinding.inflate(layoutInflater)
        listBinding = ShadeListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel.isLoading.observe(this) {
            Log.d("LoadingState", "Loading state changed: $it")
            showLoading(it)
        }

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

        selectedTone = intent.getIntExtra(EXTRA_TONE, 0)

        mainViewModel.listTone.observe(this) { skintoneList ->
            if (skintoneList != null && skintoneList.isNotEmpty()) {
                val skintone = skintoneList[0].skintone
                Log.d("Skintone", skintone)
                val textView4: TextView = findViewById(R.id.textView4)

                val typeOfSkin = getString(R.string.typeOfSkin, skintone)
                textView4.text = typeOfSkin
                showLoading(false)  // Hide the progress bar when data is loaded

            } else {
                Log.e("Skintone", "Skintone List is null or empty")
                showLoading(false)  // Hide the progress bar when data is loaded

            }
        }


        mainViewModel.findShades(selectedTone)

        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        adapter = ShadeAdapter()
        binding.recyclerView.adapter = adapter

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

        val textColor = if (selectedTone in 1..5) {
            R.color.colorTone10 // Use colorTone10 for tones 1-5
        } else {
            R.color.colorTone1 // Use colorTone1 for tones 6-10
        }
        textView4.setTextColor(resources.getColor(textColor))
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

    private fun setShadeData(listTone: List<SkintoneResponseItem>) {
        adapter.submitList(listTone)
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.textViewLoading.visibility= if (isLoading) View.VISIBLE else View.GONE
    }
}

