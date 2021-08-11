package com.estimewa.myapp.core.domain.usecase

import com.estimewa.myapp.core.data.Resource
import com.estimewa.myapp.core.domain.model.Ingredient
import com.estimewa.myapp.core.domain.model.Recipe
import com.estimewa.myapp.core.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeInteractor @Inject constructor(private val repository: IRecipeRepository) : RecipeUseCase{
    override fun getAllRecipes(): Flow<Resource<List<Recipe>>> {
        return repository.getAllRecipes()
    }

    override fun setNewRecipe(recipe: Recipe, ingredients: List<Ingredient>) {
        repository.setNewRecipe(recipe, ingredients)
    }
}