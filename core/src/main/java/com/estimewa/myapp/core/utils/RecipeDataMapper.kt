package com.estimewa.myapp.core.utils

import com.estimewa.myapp.core.data.source.local.entity.RecipeEntity
import com.estimewa.myapp.core.data.source.remote.response.ListRecipeResponse
import com.estimewa.myapp.core.domain.model.Recipe

object RecipeDataMapper {
    fun mapEntitiesToDomain(it: List<RecipeEntity>) : List<Recipe> =
        it.map {
            Recipe(
                id = it.recipeId,
                name = it.name,
                unit = it.unit,
                amount = it.amount,
            )
        }

    fun mapResponseToEntity(input: List<ListRecipeResponse>): List<RecipeEntity> =
        input.map {
            RecipeEntity(
                name = it.name,
                unit = it.unit,
                amount = it.amount,
            )
        }

    fun mapDomainToEntity(input: Recipe): RecipeEntity =
        RecipeEntity(
            name = input.name,
            unit = input.unit,
            amount = input.amount,
        )
}