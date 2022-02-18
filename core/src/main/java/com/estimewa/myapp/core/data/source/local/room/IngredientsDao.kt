package com.estimewa.myapp.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.*
import com.estimewa.myapp.core.data.source.local.entity.IngredientsEntity
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

@Dao
interface IngredientsDao {

    @Query("SELECT * FROM IngredientsTable")
    fun getAllIngredients(): Flow<List<IngredientsEntity>>

    @Query("SELECT * FROM IngredientsTable ORDER BY name ASC")
    fun getPagedIngredients(): PagingSource<Int, IngredientsEntity>

    @Query("SELECT * FROM IngredientsTable WHERE ingredientId = :id")
    fun getIngredientDetail(id: Long): Flow<IngredientsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(ingredient: IngredientsEntity): Long

    @Update
    suspend fun updateIngredient(ingredient: IngredientsEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredientList(data: List<IngredientsEntity>)

    @Query("DELETE FROM IngredientsTable")
    fun deleteIngredients()

    @Transaction
    suspend fun insertOrUpdate(entity: IngredientsEntity) : Long {
        val insertLong = insertIngredient(entity)

        Timber.d("insertLong : $insertLong of entity : $entity")

        return if (insertLong == -1L) {
            updateIngredient(entity).toLong()
        } else {
            insertLong
        }
    }
}