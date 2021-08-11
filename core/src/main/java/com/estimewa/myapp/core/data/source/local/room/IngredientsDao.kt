package com.estimewa.myapp.core.data.source.local.room

import androidx.room.*
import com.estimewa.myapp.core.data.source.local.entity.IngredientsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientsDao {

    @Query("SELECT * FROM IngredientsTable")
    fun getAllIngredients(): Flow<List<IngredientsEntity>>

    @Query("SELECT * FROM IngredientsTable WHERE ingredientId = :id")
    fun getIngredientDetail(id: Long): Flow<IngredientsEntity>

    @Insert
    suspend fun insertIngredient(ingredient: IngredientsEntity): Long

    @Update
    suspend fun updateIngredient(ingredient: IngredientsEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredientList(data: List<IngredientsEntity>)
}