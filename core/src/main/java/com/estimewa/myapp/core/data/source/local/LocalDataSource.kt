package com.estimewa.myapp.core.data.source.local

import androidx.paging.PagingSource
import com.estimewa.myapp.core.data.source.local.entity.IngredientWithRecipe
import com.estimewa.myapp.core.data.source.local.entity.IngredientsEntity
import com.estimewa.myapp.core.data.source.local.entity.RecipeEntity
import com.estimewa.myapp.core.data.source.local.entity.RecipeWithIngredient
import com.estimewa.myapp.core.data.source.local.room.IngredientsDao
import com.estimewa.myapp.core.data.source.local.room.RecipeDao
import com.estimewa.myapp.core.data.source.local.room.RecipeIngredientDao
import com.estimewa.myapp.core.domain.model.Ingredient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val ingredientsDao: IngredientsDao,
    private val recipeIngredientDao: RecipeIngredientDao,
    private val recipeDao: RecipeDao,
) {

    fun getAllIngredients(): Flow<List<IngredientsEntity>> = ingredientsDao.getAllIngredients()
    fun getIngredientDetail(id: Long): Flow<IngredientsEntity> = ingredientsDao.getIngredientDetail(id)

    //you can't use this in paging library for it only reuse this instance
    /*java.lang.IllegalStateException: An instance of PagingSource was re-used when Pager expected to create a new
    instance. Ensure that the pagingSourceFactory passed to Pager always returns a
    new instance of PagingSource.*/
    val pagedIngredients = ingredientsDao.getPagedIngredients()

    //Use this to create new instance
    fun getIngredientPaging() : PagingSource<Int, IngredientsEntity> = ingredientsDao.getPagedIngredients()

    fun deleteIngredients() = ingredientsDao.deleteIngredients()

    suspend fun insertIngredientList(data: List<IngredientsEntity>) = ingredientsDao.insertIngredientList(data)

    suspend fun insertIngredient(data: IngredientsEntity) : Long =
        ingredientsDao.insertIngredient(data)

    suspend fun updateIngredient(data: IngredientsEntity) : Int {
        return ingredientsDao.updateIngredient(data)
    }

    suspend fun insertOrUpdateIng(data: IngredientsEntity) : Long =
        ingredientsDao.insertOrUpdate(data)

    fun getRecipeWithIngredients(): Flow<List<RecipeWithIngredient>> = recipeIngredientDao.getRecipeWithIngredients()

    fun getAllRecipe(): Flow<List<RecipeEntity>> = recipeDao.getAllRecipe()

    suspend fun insertRecipes(recipes: List<RecipeEntity>) {
        recipeDao.insertRecipe(recipes)
    }

    /*suspend fun insertRecipeWithIngredient(data: RecipeWithIngredient) {
        recipeIngredientDao.insertRecipeWithIngredients(data)
    }

    suspend fun insertIngredientWithRecipes(data: IngredientWithRecipe) {
        recipeIngredientDao.insertIngredientWithRecipes(data)
    }*/
}