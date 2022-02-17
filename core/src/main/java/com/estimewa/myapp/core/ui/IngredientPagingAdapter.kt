package com.estimewa.myapp.core.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.estimewa.myapp.core.R
import com.estimewa.myapp.core.databinding.ItemListIngredientsBinding
import com.estimewa.myapp.core.domain.model.Ingredient

class IngredientPagingAdapter :
    PagingDataAdapter<Ingredient, IngredientPagingAdapter.ListViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ingredient>() {
            override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean =
                oldItem.ingredientId == newItem.ingredientId

            override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean =
                oldItem == newItem
        }
    }

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListIngredientsBinding.bind(view)
        val context: Context = view.context

        fun bind(item: Ingredient?) {
            with(binding){
                tvIngredientName.text = item?.name
                tvInitial.text = item?.name?.take(2)
                tvPricePerUnit.text = view.context.getString(R.string.price_per_unit, item?.price, item?.unit)
            }
        }

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_ingredients, parent, false)
        )
}