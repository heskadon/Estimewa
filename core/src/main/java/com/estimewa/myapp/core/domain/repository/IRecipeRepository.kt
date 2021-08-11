package com.estimewa.myapp.core.domain.repository

import com.estimewa.myapp.core.data.Resource
import com.estimewa.myapp.core.domain.model.Ingredient
import com.estimewa.myapp.core.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface IRecipeRepository {
    fun getAllRecipes(): Flow<Resource<List<Recipe>>>
    fun setNewRecipe(recipe: Recipe, ingredients: List<Ingredient>)
}