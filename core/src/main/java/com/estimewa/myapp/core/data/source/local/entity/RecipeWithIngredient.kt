package com.estimewa.myapp.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class RecipeWithIngredient(
    @Embedded val recipeEntity: RecipeEntity,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "ingredientId",
        associateBy = Junction(IngredientRecipeCrossReference::class)
    )
    val ingredient: List<IngredientsEntity>
)
