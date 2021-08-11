package com.estimewa.myapp.core.domain.model

data class Ingredient(
    val ingredientId: Long? = null,
    val name: String,
    val price: String,
    val unit: String,
    val amount: Int,
    val seller: String,
)
