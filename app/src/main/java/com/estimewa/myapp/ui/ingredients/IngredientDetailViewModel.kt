package com.estimewa.myapp.ui.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.estimewa.myapp.core.domain.usecase.IngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IngredientDetailViewModel @Inject constructor(useCase: IngredientUseCase) : ViewModel() {
    private var _ingredientId: Long? = null

    val ingredientId: Long? get() = _ingredientId

    val getIngredientDetail = { id: Long ->
        useCase.getIngredientDetail(id).asLiveData()
    }

    fun setId(id: Long) {
        _ingredientId = id
    }
}