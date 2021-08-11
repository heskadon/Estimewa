package com.estimewa.myapp.core.di

import android.content.Context
import androidx.room.Room
import com.estimewa.myapp.core.data.source.local.room.AppDatabase
import com.estimewa.myapp.core.data.source.local.room.IngredientsDao
import com.estimewa.myapp.core.data.source.local.room.RecipeDao
import com.estimewa.myapp.core.data.source.local.room.RecipeIngredientDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "Estimewa.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideIngredientDao(database: AppDatabase): IngredientsDao = database.ingredientsDao()

    @Provides
    fun provideRecipeIngredientDao(database: AppDatabase): RecipeIngredientDao = database.recipeIngredientsDao()

    @Provides
    fun provideRecipeDao(database: AppDatabase): RecipeDao = database.recipeDao()
}