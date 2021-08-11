package com.estimewa.myapp.core.data.source.local.entity

import androidx.room.Entity

@Entity(primaryKeys = ["ingredientId","recipeId"])
data class IngredientRecipeCrossReference(
    val ingredientId: Long,
    val recipeId: Long,
)
