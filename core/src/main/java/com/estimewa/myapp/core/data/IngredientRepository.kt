package com.estimewa.myapp.core.data

import com.estimewa.myapp.core.data.source.local.LocalDataSource
import com.estimewa.myapp.core.data.source.remote.RemoteDataSource
import com.estimewa.myapp.core.data.source.remote.network.ApiResponse
import com.estimewa.myapp.core.data.source.remote.response.ListIngredientsResponse
import com.estimewa.myapp.core.domain.model.Ingredient
import com.estimewa.myapp.core.domain.repository.IIngredientRepository
import com.estimewa.myapp.core.utils.IngredientsDataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IngredientRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IIngredientRepository {
    override fun getAllIngredients(): Flow<Resource<List<Ingredient>>> {
        return object : NetworkBoundResource<List<Ingredient>, List<ListIngredientsResponse>>() {
            override fun loadFromDB(): Flow<List<Ingredient>> {
                return localDataSource.getAllIngredients().map {
                    IngredientsDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Ingredient>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ListIngredientsResponse>>> =
                remoteDataSource.getIngredientDummy()

            override suspend fun saveCallResult(data: List<ListIngredientsResponse>) {
                val ingredients = IngredientsDataMapper.mapResponseToEntity(data)
                localDataSource.insertIngredientList(ingredients)
            }

        }.asFlow()
    }

    override fun saveIngredient(ingredient: Ingredient): Flow<Long> {
        val data = IngredientsDataMapper.mapDomainToEntity(ingredient)
        return flow {
            emit(localDataSource.insertIngredient(data))
        }.flowOn(Dispatchers.IO)
    }

    override fun updateIngredient(ingredient: Ingredient): Flow<Int> {
        val data = IngredientsDataMapper.mapDomainToEntity(ingredient)
        return flow {
            emit(localDataSource.updateIngredient(data))
        }
    }

    override fun getIngredientDetail(id: Long): Flow<Ingredient> {
        return localDataSource.getIngredientDetail(id).map {
            IngredientsDataMapper.mapEntityToDomain(it)
        }
    }

}