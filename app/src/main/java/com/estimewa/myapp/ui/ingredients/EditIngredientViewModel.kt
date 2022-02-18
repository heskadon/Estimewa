package com.estimewa.myapp.ui.ingredients

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.estimewa.myapp.core.domain.model.Ingredient
import com.estimewa.myapp.core.domain.usecase.IngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditIngredientViewModel @Inject constructor(private val useCase: IngredientUseCase) :
    ViewModel() {

    val itemDetail = { id: Long -> useCase.getIngredientDetail(id).asLiveData() }

    fun saveIngredient(data: Ingredient): LiveData<Long> = useCase.insertOrUpdateIngredient(data).asLiveData()
}