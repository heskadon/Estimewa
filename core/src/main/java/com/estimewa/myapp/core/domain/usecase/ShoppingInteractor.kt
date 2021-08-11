package com.estimewa.myapp.core.domain.usecase

import com.estimewa.myapp.core.data.Resource
import com.estimewa.myapp.core.domain.model.Shopping
import com.estimewa.myapp.core.domain.repository.IShoppingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingInteractor @Inject constructor(private val repository: IShoppingRepository) : ShoppingUseCase {
    override fun getShoppingList(): Flow<Resource<List<Shopping>>> {
        return repository.getShoppingList()
    }
}