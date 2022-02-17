package com.estimewa.myapp.core.utils

import com.estimewa.myapp.core.data.source.remote.response.ListIngredientsResponse
import com.estimewa.myapp.core.data.source.remote.response.ListRecipeResponse
import com.estimewa.myapp.core.domain.model.Ingredient
import com.estimewa.myapp.core.domain.model.Recipe
import com.estimewa.myapp.core.domain.model.RecipeIngredients
import com.estimewa.myapp.core.domain.model.Shopping
import com.mooveit.library.Fakeit
import java.util.*
import kotlin.collections.ArrayList

object DummyDataSource {
    fun getDummyRecipes() = listOf(
        Recipe(1, "Rawon", "porsi", 150),
        Recipe(2, "Soto", "porsi", 100),
        Recipe(3, "Bakso", "porsi", 10),
        Recipe(4, "Sambal Goreng Kacang Goreng", "kg", 5),
        Recipe(1, "Rawon", "porsi", 150),
        Recipe(2, "Soto", "porsi", 100),
        Recipe(3, "Bakso", "porsi", 10),
        Recipe(4, "Sambal Goreng Kacang Goreng", "kg", 5),
        Recipe(1, "Rawon", "porsi", 150),
        Recipe(2, "Soto", "porsi", 100),
        Recipe(3, "Bakso", "porsi", 10),
        Recipe(4, "Sambal Goreng Kacang Goreng", "kg", 5),
        Recipe(1, "Rawon", "porsi", 150),
        Recipe(2, "Soto", "porsi", 100),
        Recipe(3, "Bakso", "porsi", 10),
        Recipe(4, "Sambal Goreng Kacang Goreng", "kg", 5),
        Recipe(1, "Rawon", "porsi", 150),
        Recipe(2, "Soto", "porsi", 100),
        Recipe(3, "Bakso", "porsi", 10),
        Recipe(4, "Sambal Goreng Kacang Goreng", "kg", 5),
        Recipe(1, "Rawon", "porsi", 150),
        Recipe(2, "Soto", "porsi", 100),
        Recipe(3, "Bakso", "porsi", 10),
        Recipe(4, "Sambal Goreng Kacang Goreng", "kg", 5),
    )

    fun getDummyShoppingList() = listOf(
        Shopping(1, "Rawon", 100, "porsi", 3, "resep"),
        Shopping(2, "Soto", 100, "porsi", 3, "resep"),
        Shopping(3, "Papeda", 100, "porsi", 3, "resep"),
        Shopping(4, "Ayam Woku", 100, "porsi", 3, "resep"),
    )

    fun getDummyRecipeIngredient(): List<RecipeIngredients> {
        val data = ArrayList<RecipeIngredients>()

        for (i in 1..100) {
            val rec = Recipe(
                id = i.toLong(),
                name = "Rawon",
                unit = "resep",
                amount = 1,
            )
            val ing = ArrayList<Ingredient>()
            for (j in 1..5) {
                ing.add(
                    Ingredient(
                        ingredientId = j.toLong(),
                        name = "Tomat",
                        price = "20000",
                        unit = "kg",
                        amount = 2,
                        seller = "Ronaldo"
                    )
                )
            }

            data.add(RecipeIngredients(rec, ing))
        }

        return data
    }

    fun getDummyIngredient() : List<ListIngredientsResponse> {
        val data = ArrayList<ListIngredientsResponse>()

        for (i in 1..1000) {
            val l = ListIngredientsResponse(
                id = i.toString(),
                name = Fakeit.food().ingredient(),
                price = "1000",
                unit = "g",
                amount = 1,
                seller = Fakeit.name().firstName()
            )
            data.add(l)
        }
        return data
    }

    fun getDummyRecipe() : List<ListRecipeResponse> {
        val data = ArrayList<ListRecipeResponse>()

        for (i in 1..1000) {
            val l = ListRecipeResponse(
                id = i.toString(),
                name = Fakeit.food().spice(),
                unit = "bowl",
                amount = 1,
            )
            data.add(l)
        }
        return data
    }
}