package com.estimewa.myapp.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class IngredientWithRecipe(
    @Embedded val ingredientsEntity: IngredientsEntity,
    @Relation(
        parentColumn = "ingredientId",
        entityColumn = "recipeId",
        associateBy = Junction(IngredientRecipeCrossReference::class)
    )
    val recipe: List<RecipeEntity>
)
