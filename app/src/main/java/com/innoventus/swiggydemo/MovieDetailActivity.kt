package com.innoventus.swiggydemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.innoventus.swiggydemo.databinding.ActivityMainBinding
import com.innoventus.swiggydemo.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private var _binding: ActivityMovieDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
    }

    private fun getIntentData() {
        intent.extras?.let {
            if (it.containsKey("item")) {
                binding.item = Gson().fromJson(it.getString("item"), Search::class.java)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}