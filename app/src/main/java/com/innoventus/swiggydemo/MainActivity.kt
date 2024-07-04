package com.innoventus.swiggydemo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.google.gson.Gson
import com.innoventus.swiggydemo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)
        callApi()
    }

    private fun callApi() = with(lifecycleScope) {
        /*binding.etSearch.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                viewModel.query.postValue(it.toString())
                launch(Dispatchers.IO) {
                    viewModel.flow.collectLatest {
                        withContext(Dispatchers.Main) {
                            setRecyclerView(it)
                        }
                    }
                }
            }
        }*/
        binding.etSearch.addTextChangedListener {
            launch(Dispatchers.Main) {
                if (it.toString().isNotEmpty()) {
                    delay(2000)
                    withContext(Dispatchers.IO) {
                        RetrofitBuilder.getInstance().getMoviesList(binding.etSearch.text.toString(), 1).enqueue(object :
                            Callback<MovieUiModel> {
                            @SuppressLint("SetTextI18n")
                            override fun onResponse(
                                p0: Call<MovieUiModel>,
                                p1: Response<MovieUiModel>
                            ) {
                                runOnUiThread {
                                    binding.etSearch.setText("")
                                    setRecyclerView(p1.body() ?: MovieUiModel())
                                }
                                Log.e(TAG, "onResponse: " + p1.body() )
                            }

                            override fun onFailure(p0: Call<MovieUiModel>, p1: Throwable) {
                                Log.e(TAG, "onFailure: " + p1.printStackTrace())
                            }
                        })
                    }
                }
            }
        }
    }

    /*private fun setRecyclerView(data: PagingData<Search>) = with(binding.recyclerView) {
        val movieAdapter = MovieAdapter(object : OnItemClickListener {
            override fun onItemClick(view: View, item: Search) {
                binding.etSearch.clearFocus()
                startActivity(
                    Intent(this@MainActivity, MovieDetailActivity::class.java).putExtra(
                        "item",
                        Gson().toJson(item)
                    )
                )
            }
        })
        lifecycleScope.launch(Dispatchers.IO) {
            movieAdapter.submitData(data)
        }
        adapter = movieAdapter
    }*/

    private fun setRecyclerView(data: MovieUiModel) = with(binding.recyclerView) {
        val movieAdapter = MovieAdapter(data.Search, object : OnItemClickListener {
            override fun onItemClick(view: View, item: Search) {
                binding.etSearch.clearFocus()
                startActivity(
                    Intent(this@MainActivity, MovieDetailActivity::class.java).putExtra(
                        "item",
                        Gson().toJson(item)
                    )
                )
            }
        })
        adapter = movieAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}