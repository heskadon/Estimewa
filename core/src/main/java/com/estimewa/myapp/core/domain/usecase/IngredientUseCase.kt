package com.estimewa.myapp.core.domain.usecase

import com.estimewa.myapp.core.data.Resource
import com.estimewa.myapp.core.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientUseCase {
    fun getAllIngredients(): Flow<Resource<List<Ingredient>>>
    fun saveIngredient(ingredient: Ingredient): Flow<Long>
    fun updateIngredient(ingredient: Ingredient): Flow<Int>
    fun getIngredientDetail(id: Long): Flow<Ingredient>
}