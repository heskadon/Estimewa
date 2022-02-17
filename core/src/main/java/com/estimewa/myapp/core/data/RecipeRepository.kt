package com.estimewa.myapp.core.data

import com.estimewa.myapp.core.data.source.local.LocalDataSource
import com.estimewa.myapp.core.data.source.local.entity.RecipeWithIngredient
import com.estimewa.myapp.core.data.source.remote.RemoteDataSource
import com.estimewa.myapp.core.data.source.remote.network.ApiResponse
import com.estimewa.myapp.core.data.source.remote.response.ListRecipeResponse
import com.estimewa.myapp.core.domain.model.Ingredient
import com.estimewa.myapp.core.domain.model.Recipe
import com.estimewa.myapp.core.domain.repository.IRecipeRepository
import com.estimewa.myapp.core.utils.IngredientsDataMapper
import com.estimewa.myapp.core.utils.RecipeDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
): IRecipeRepository {
    override fun getAllRecipes(): Flow<Resource<List<Recipe>>> =
        object : NetworkBoundResource<List<Recipe>, List<ListRecipeResponse>>(){
            override fun loadFromDB(): Flow<List<Recipe>> =
                localDataSource.getAllRecipe().map {
                    RecipeDataMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Recipe>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ListRecipeResponse>>> =
                remoteDataSource.getPrePopulateRecipe()

            override suspend fun saveCallResult(data: List<ListRecipeResponse>) {
                val recipes = RecipeDataMapper.mapResponseToEntity(data)
                localDataSource.insertRecipes(recipes)
            }

        }.asFlow()

    override fun setNewRecipe(recipe: Recipe, ingredients: List<Ingredient>) {

    }

}