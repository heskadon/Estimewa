package com.estimewa.myapp.core.data.source.remote.network

import com.estimewa.myapp.core.data.source.remote.response.ListIngredientsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("list")
    suspend fun getIngredients(): List<ListIngredientsResponse>
}