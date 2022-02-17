package com.estimewa.myapp

import androidx.lifecycle.ViewModel
import com.estimewa.myapp.core.domain.usecase.IngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val ingredientUseCase: IngredientUseCase) :
    ViewModel() {
}