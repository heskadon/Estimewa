package com.estimewa.myapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.estimewa.myapp.core.R
import com.estimewa.myapp.core.databinding.ItemListRecipeBinding
import com.estimewa.myapp.core.domain.model.Recipe

class RecipeListAdapter : ListAdapter<Recipe, RecipeListAdapter.ListViewHolder>(DiffCallback()) {

    var onItemClick: ((Recipe) -> Unit)? = null

    private class DiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
            oldItem == newItem

    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListRecipeBinding.bind(itemView)
        fun bind(item: Recipe?) {
            with(binding){
                if (item != null){
                    tvRecipeName.text = item.name
                    tvRecipeAmount.text = item.amount.toString()
                    tvRecipeAmountUnit.text = item.unit
                    tvInitial.text = item.name.take(2)
                }
            }
        }

        init {
            binding.root.setOnClickListener{
                onItemClick?.invoke(currentList[bindingAdapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_recipe, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }


}