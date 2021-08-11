package com.estimewa.myapp.di

import com.estimewa.myapp.core.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
abstract class MyAppModule {

    @Binds
    abstract fun provideIngredientUseCase(interactor: IngredientInteractor): IngredientUseCase

    @Binds
    abstract fun provideRecipeUseCase(interactor: RecipeInteractor): RecipeUseCase

    @Binds
    abstract fun provideShoppingUseCase(interactor: ShoppingInteractor): ShoppingUseCase
}