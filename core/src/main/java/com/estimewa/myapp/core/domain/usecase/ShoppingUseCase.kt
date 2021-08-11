package com.estimewa.myapp.core.domain.usecase

import com.estimewa.myapp.core.data.Resource
import com.estimewa.myapp.core.domain.model.Shopping
import kotlinx.coroutines.flow.Flow

interface ShoppingUseCase {
    fun getShoppingList(): Flow<Resource<List<Shopping>>>
}