package com.estimewa.myapp.ui.ingredients

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.estimewa.myapp.core.domain.model.Ingredient
import com.estimewa.myapp.databinding.ActivityEditIngredientBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EditIngredientActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditIngredientBinding
    private val viewModel: EditIngredientViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initWidget()
    }

    private fun initWidget() {
        with(binding) {
            efabSaveIngredient.setOnClickListener {
                editIngredient()
            }
        }
    }

    private fun editIngredient() {
        with(binding) {
            val name = tietIngName.text.toString()
            val price = tietIngPrice.text.toString()
            val unit = tietIngUnit.text.toString()
            val amount = tietIngredientAmount.text.toString()
            val seller = tietSeller.text.toString()

            if (name.isEmpty()) {
                tietIngName.requestFocus()

                return
            }

            val data = Ingredient(
                name = name,
                price = price,
                unit = unit,
                seller = seller,
                amount = amount.toInt()
            )

            viewModel.saveIngredient(data).observe(this@EditIngredientActivity, {
                if (it != 0L){
                    Timber.d("saveIngredient = $it")
                    finish()
                } else {
                    Timber.e("Something wrong")
                }
            })

        }
    }
}