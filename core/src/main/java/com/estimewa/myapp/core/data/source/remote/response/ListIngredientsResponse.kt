package com.estimewa.myapp.core.data.source.remote.response

data class ListIngredientsResponse(
    val id: String? = null,
    val name: String? = null,
    val price: String? = null,
    val unit: String? = null,
    val amount: Int?,
    val seller: String?
)
