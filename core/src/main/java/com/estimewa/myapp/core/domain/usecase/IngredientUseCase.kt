package com.estimewa.myapp.core.domain.usecase

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.estimewa.myapp.core.data.Resource
import com.estimewa.myapp.core.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientUseCase {
    fun getAllIngredients(): Flow<Resource<List<Ingredient>>>
    fun saveIngredient(ingredient: Ingredient): Flow<Long>
    fun updateIngredient(ingredient: Ingredient): Flow<Int>
    fun getIngredientDetail(id: Long): Flow<Ingredient>
    fun getPagedIngredients(): Flow<PagingData<Ingredient>>
    fun insertOrUpdateIngredient(ingredient: Ingredient): Flow<Long>
}