package com.estimewa.myapp.core.domain.repository

import androidx.paging.PagingData
import com.estimewa.myapp.core.data.Resource
import com.estimewa.myapp.core.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IIngredientRepository {
    fun getAllIngredients(): Flow<Resource<List<Ingredient>>>
    fun saveIngredient(ingredient: Ingredient): Flow<Long>
    fun updateIngredient(ingredient: Ingredient): Flow<Int>
    fun getIngredientDetail(id: Long): Flow<Ingredient>
    fun getPagedIngredients(): Flow<PagingData<Ingredient>>
}