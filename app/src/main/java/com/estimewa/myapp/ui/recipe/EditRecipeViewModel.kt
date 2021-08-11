package com.estimewa.myapp.ui.recipe

import androidx.lifecycle.ViewModel
import com.estimewa.myapp.core.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditRecipeViewModel @Inject constructor(useCase: RecipeUseCase): ViewModel() {

}