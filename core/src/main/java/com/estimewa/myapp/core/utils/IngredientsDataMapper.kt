package com.estimewa.myapp.core.utils

import com.estimewa.myapp.core.data.source.local.entity.IngredientsEntity
import com.estimewa.myapp.core.data.source.remote.response.ListIngredientsResponse
import com.estimewa.myapp.core.domain.model.Ingredient

object IngredientsDataMapper {
    fun mapEntitiesToDomain(input: List<IngredientsEntity>): List<Ingredient> {
        return input.map {
            Ingredient(
                ingredientId = it.ingredientId,
                name = it.name,
                price = it.price,
                unit = it.unit,
                amount = it.amount,
                seller = it.seller,
            )
        }
    }

    fun mapEntityToDomain(it: IngredientsEntity) =
        Ingredient(
            ingredientId = it.ingredientId,
            name = it.name,
            price = it.price,
            unit = it.unit,
            amount = it.amount,
            seller = it.seller,
        )

    fun mapDomainToEntity(input: Ingredient) =
        IngredientsEntity(
            ingredientId = input.ingredientId,
            name = input.name,
            price = input.price,
            unit = input.unit,
            amount = input.amount,
            seller = input.seller,
        )

    fun mapResponseToEntity(input: List<ListIngredientsResponse>): List<IngredientsEntity> =
        input.map {
            IngredientsEntity(
                name = it.name.toString(),
                price = it.price.toString(),
                unit = it.unit.toString(),
                amount = it.amount?: 0,
                seller = it.seller.toString(),
            )
        }
}