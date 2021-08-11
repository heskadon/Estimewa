package com.estimewa.myapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.estimewa.myapp.core.R
import com.estimewa.myapp.core.databinding.ItemListIngredientsBinding
import com.estimewa.myapp.core.domain.model.Ingredient

class IngredientListAdapter :
    ListAdapter<Ingredient, IngredientListAdapter.ListViewHolder>(DiffCallback()) {

    var onItemClick: ((Ingredient) -> Unit)? = null

    private class DiffCallback : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.ingredientId == newItem.ingredientId
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean =
            oldItem == newItem

    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemListIngredientsBinding.bind(itemView)

        fun bind(item: Ingredient?) {
            if (item != null){
                with(binding){
                    tvIngredientName.text = item.name
                    tvInitial.text = item.name.take(2)
                    tvPricePerUnit.text = "Rp ${item.price} per ${item.amount} ${item.unit}"
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(currentList[bindingAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_ingredients, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }
}