package com.estimewa.myapp.core.data.source.remote.response

data class ListRecipeResponse(
    var id: Long? = null,
    var name: String,
    var unit: String,
    var amount: Int,
)
