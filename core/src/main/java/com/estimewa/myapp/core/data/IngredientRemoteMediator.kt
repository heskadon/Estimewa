package com.estimewa.myapp.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.estimewa.myapp.core.data.source.local.LocalDataSource
import com.estimewa.myapp.core.data.source.remote.RemoteDataSource
import com.estimewa.myapp.core.data.source.remote.response.ListIngredientsResponse
import com.estimewa.myapp.core.utils.DummyDataSource
import com.estimewa.myapp.core.utils.IngredientsDataMapper
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class IngredientRemoteMediator @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) :
    RemoteMediator<Int, ListIngredientsResponse>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ListIngredientsResponse>
    ): MediatorResult {
        return try {
            // The network load method takes an optional after=<user.id>
            // parameter. For every page after the first, pass the last user
            // ID to let it continue from where it left off. For REFRESH,
            // pass null to load the first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.
                    lastItem.id
                }
            }

            val response = mapOf(1 to "end")
            val dataSource = DummyDataSource.getDummyIngredient()
            if (loadType == LoadType.REFRESH) {
                localDataSource.deleteIngredients()
            }
            localDataSource.insertIngredientList(IngredientsDataMapper.mapResponseToEntity(dataSource))

            MediatorResult.Success(
                endOfPaginationReached = response[0] == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}