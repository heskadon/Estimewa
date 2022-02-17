package com.estimewa.myapp.ui.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.estimewa.myapp.core.domain.usecase.IngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(useCase: IngredientUseCase) : ViewModel() {
    val ingredientList = useCase.getAllIngredients().asLiveData()

    val pagedIngredient = useCase.getPagedIngredients().cachedIn(viewModelScope).asLiveData()

    val ingredients = useCase.getPagedIngredients().cachedIn(viewModelScope)
}