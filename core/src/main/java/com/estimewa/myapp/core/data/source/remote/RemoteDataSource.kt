package com.estimewa.myapp.core.data.source.remote

import com.estimewa.myapp.core.data.source.remote.network.ApiResponse
import com.estimewa.myapp.core.data.source.remote.network.ApiService
import com.estimewa.myapp.core.data.source.remote.response.ListIngredientsResponse
import com.estimewa.myapp.core.data.source.remote.response.ListRecipeResponse
import com.estimewa.myapp.core.utils.DummyDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService){

    suspend fun getIngredientList(): Flow<ApiResponse<List<ListIngredientsResponse>>> {
        return flow {
            try {
                val response = apiService.getIngredients()
                if (!response.isNullOrEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getIngredientDummy(): Flow<ApiResponse<List<ListIngredientsResponse>>> {
        return flow {
            val dummy = DummyDataSource.getDummyIngredient()

            emit(ApiResponse.Success(dummy))
        }.flowOn(Dispatchers.IO)
    }

    fun getPrePopulateRecipe(): Flow<ApiResponse<List<ListRecipeResponse>>> {
        return flow {
            val dummyData = ArrayList<ListRecipeResponse>()

            for (i in 1..50){
                dummyData.add(ListRecipeResponse(
                    id = i.toString(),
                    name = "Birthday Cake $i",
                    unit = "pcs",
                    amount = 2,
                ))
            }
            emit(ApiResponse.Success(dummyData))
        }.flowOn(Dispatchers.IO)
    }


}