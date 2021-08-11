package com.estimewa.myapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.estimewa.myapp.core.data.source.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM RecipeTable")
    fun getAllRecipe(): Flow<List<RecipeEntity>>

    @Insert
    suspend fun insertRecipe(recipes: List<RecipeEntity>)
}