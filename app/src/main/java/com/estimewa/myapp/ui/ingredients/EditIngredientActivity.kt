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
    private var _ingredientId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initWidget()

        bundleData()
    }

    private fun bundleData() {
        _ingredientId = intent.getLongExtra("id",0L)
        Timber.d("ingredientId extras : $_ingredientId")

        if (_ingredientId != null && _ingredientId != 0L){
            viewModel.itemDetail(_ingredientId!!).observe(this@EditIngredientActivity){
                bindToView(it)
            }
        }
    }

    private fun bindToView(it: Ingredient?) {
        if (it!=null){
            with(binding){
                tietIngName.setText(it.name)
                tietIngPrice.setText(it.price)
                tietIngredientAmount.setText(it.amount.toString())
                tietIngUnit.setText(it.unit)
                tietSeller.setText(it.seller)
            }
        }
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

            val id = if (_ingredientId!=0L) _ingredientId else null

            val data = Ingredient(
                name = name,
                price = price,
                unit = unit,
                seller = seller,
                amount = amount.toInt(),
                ingredientId = id
            )

            viewModel.saveIngredient(data).observe(this@EditIngredientActivity) {
                if (it != 0L) {
                    Timber.d("saveIngredient = $it")
                    finish()
                } else {
                    Timber.e("Something wrong")
                }
            }

        }
    }
}