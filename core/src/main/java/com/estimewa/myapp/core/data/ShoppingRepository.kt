package com.estimewa.myapp.core.data

import com.estimewa.myapp.core.domain.model.Shopping
import com.estimewa.myapp.core.domain.repository.IShoppingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingRepository @Inject constructor() : IShoppingRepository {
    override fun getShoppingList(): Flow<Resource<List<Shopping>>> {
        return flow {  }
    }
}