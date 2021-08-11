package com.estimewa.myapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "IngredientsTable")
data class IngredientsEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ingredientId", index = true)
    var ingredientId: Long? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "price")
    var price: String,

    @ColumnInfo(name = "unit")
    var unit: String,

    @ColumnInfo(name = "amount")
    var amount: Int,

    @ColumnInfo(name = "seller")
    var seller: String,
)