package com.estimewa.myapp.core.domain.model

data class Recipe(
    val id: Long?,
    val name: String,
    val unit: String,
    val amount: Int,
    val ingredients: List<Ingredient>? = null,
)
