package com.estimewa.myapp.core.utils

import com.estimewa.myapp.core.domain.model.Recipe
import com.estimewa.myapp.core.domain.model.Shopping

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
}