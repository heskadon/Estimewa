package com.estimewa.myapp.ui.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.estimewa.myapp.core.domain.usecase.IngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(useCase: IngredientUseCase) : ViewModel() {
    val ingredientList = useCase.getAllIngredients().asLiveData()
}