package com.estimewa.myapp.core.domain.usecase

import androidx.paging.PagingData
import com.estimewa.myapp.core.data.Resource
import com.estimewa.myapp.core.domain.model.Ingredient
import com.estimewa.myapp.core.domain.repository.IIngredientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IngredientInteractor @Inject constructor(private val repository: IIngredientRepository) :
    IngredientUseCase {
    override fun getAllIngredients(): Flow<Resource<List<Ingredient>>> {
        return repository.getAllIngredients()
    }

    override fun saveIngredient(ingredient: Ingredient): Flow<Long> {
        return repository.saveIngredient(ingredient)
    }

    override fun updateIngredient(ingredient: Ingredient): Flow<Int> {
        return repository.updateIngredient(ingredient)
    }

    override fun getIngredientDetail(id: Long): Flow<Ingredient> {
        return repository.getIngredientDetail(id)
    }

    override fun getPagedIngredients(): Flow<PagingData<Ingredient>> {
        return repository.getPagedIngredients()
    }

    override fun insertOrUpdateIngredient(ingredient: Ingredient): Flow<Long> {
        return repository.insertOrUpdateIngredient(ingredient)
    }
}