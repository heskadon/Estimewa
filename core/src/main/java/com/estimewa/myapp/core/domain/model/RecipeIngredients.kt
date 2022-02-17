package com.estimewa.myapp.core.domain.model

data class RecipeIngredients(
    val recipe: Recipe,
    val ingredients: List<Ingredient>,
)
