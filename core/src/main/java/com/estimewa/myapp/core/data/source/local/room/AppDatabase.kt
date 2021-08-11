package com.estimewa.myapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.estimewa.myapp.core.data.source.local.entity.*

@Database(
    entities = [
        IngredientsEntity::class,
        RecipeEntity::class,
        IngredientRecipeCrossReference::class,

    ],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ingredientsDao(): IngredientsDao

    abstract fun recipeIngredientsDao(): RecipeIngredientDao

    abstract fun recipeDao(): RecipeDao

}