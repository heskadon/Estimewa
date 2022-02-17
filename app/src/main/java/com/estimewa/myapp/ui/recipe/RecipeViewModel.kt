package com.estimewa.myapp.ui.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.estimewa.myapp.core.domain.model.Recipe
import com.estimewa.myapp.core.domain.usecase.RecipeUseCase
import com.estimewa.myapp.core.utils.DummyDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(useCase: RecipeUseCase) : ViewModel() {
    val recipeList = useCase.getAllRecipes().asLiveData()

    private val _dummy = MutableLiveData<List<Recipe>>().apply {

        value = DummyDataSource.getDummyRecipes()
    }
    val dummy: LiveData<List<Recipe>> = _dummy

    init {
        Timber.d("init RecipeViewModel")
    }
}