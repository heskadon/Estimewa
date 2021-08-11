package com.estimewa.myapp.core.di

import com.estimewa.myapp.core.data.IngredientRepository
import com.estimewa.myapp.core.data.RecipeRepository
import com.estimewa.myapp.core.data.ShoppingRepository
import com.estimewa.myapp.core.domain.repository.IIngredientRepository
import com.estimewa.myapp.core.domain.repository.IRecipeRepository
import com.estimewa.myapp.core.domain.repository.IShoppingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideIngredientsRepository(repo: IngredientRepository): IIngredientRepository

    @Binds
    abstract fun provideRecipeRepository(repo: RecipeRepository): IRecipeRepository

    @Binds
    abstract fun provideShoppingRepository(repo: ShoppingRepository): IShoppingRepository
}