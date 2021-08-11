package com.estimewa.myapp.core.domain.model

data class Shopping(
    val id: Long,
    val menuName: String,
    val menuAmount: Int,
    val menuAmountUnit: String,
    val recipeAmount: Int,
    val recipeAmountUnit: String,
)
