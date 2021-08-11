package com.estimewa.myapp.core.domain.repository

import com.estimewa.myapp.core.data.Resource
import com.estimewa.myapp.core.domain.model.Shopping
import kotlinx.coroutines.flow.Flow

interface IShoppingRepository {
    fun getShoppingList(): Flow<Resource<List<Shopping>>>
}