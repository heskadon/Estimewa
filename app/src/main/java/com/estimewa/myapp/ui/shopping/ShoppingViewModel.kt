package com.estimewa.myapp.ui.shopping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.estimewa.myapp.core.domain.model.Recipe
import com.estimewa.myapp.core.domain.model.Shopping
import com.estimewa.myapp.core.domain.usecase.ShoppingUseCase
import com.estimewa.myapp.core.utils.DummyDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(useCase: ShoppingUseCase) : ViewModel() {

    val shoppingList = useCase.getShoppingList().asLiveData()

    private val _dummy = MutableLiveData<List<Shopping>>().apply {

        value = DummyDataSource.getDummyShoppingList()
    }
    val dummy: LiveData<List<Shopping>> = _dummy


}