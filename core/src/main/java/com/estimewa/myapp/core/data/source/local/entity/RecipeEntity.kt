package com.estimewa.myapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecipeTable")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "recipeId", index = true)
    var recipeId: Long? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "unit")
    var unit: String,

    @ColumnInfo(name = "amount")
    var amount: Int,
)
