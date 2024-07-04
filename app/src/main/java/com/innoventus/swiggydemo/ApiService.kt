package com.innoventus.swiggydemo

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("?apikey=f65dd4a5&")
    fun getMoviesList(
        @Query("s") search: String,
        @Query("page") page: Int): Call<MovieUiModel>

}