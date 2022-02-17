package com.estimewa.myapp.core.data.source.local.room

import androidx.room.*
import com.estimewa.myapp.core.data.source.local.entity.IngredientRecipeCrossReference
import com.estimewa.myapp.core.data.source.local.entity.IngredientWithRecipe
import com.estimewa.myapp.core.data.source.local.entity.RecipeWithIngredient
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeIngredientDao {

    @Transaction
    @Query("SELECT * FROM RecipeTable")
    fun getRecipeWithIngredients(): Flow<List<RecipeWithIngredient>>

    @Transaction
    @Query("SELECT * FROM IngredientsTable")
    fun getIngredientWithRecipes(): Flow<List<IngredientWithRecipe>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeWithIngredients(data: IngredientRecipeCrossReference)

}