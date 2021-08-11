package com.estimewa.myapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.estimewa.myapp.core.R
import com.estimewa.myapp.core.databinding.ItemListShoppingBinding
import com.estimewa.myapp.core.domain.model.Shopping

class ShoppingListAdapter :
    ListAdapter<Shopping, ShoppingListAdapter.ListViewHolder>(DiffCallback()) {

    var tracker: SelectionTracker<Long>? = null
    var onItemClick: ((Shopping) -> Unit)? = null

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListShoppingBinding.bind(itemView)

        fun bind(item: Shopping?, isSelected: Boolean, hasSelection: Boolean) {
            if (item!=null){
                with(binding){
                    tvMenuName.text = item.menuName
                    tvMenuAmount.text = item.menuAmount.toString()
                    tvMenuAmountUnit.text = item.menuAmountUnit
                    tvRecipeCount.text = item.recipeAmount.toString()
                    tvRecipeUnitCount.text = item.recipeAmountUnit

                    if (hasSelection) {
                        ivCheck.isVisible = isSelected
                        ivNoCheck.isVisible = !isSelected
                    } else {
                        ivCheck.isVisible = false
                        ivNoCheck.isVisible = false
                    }
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(currentList[bindingAdapterPosition])
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = bindingAdapterPosition
                override fun getSelectionKey(): Long {
                    return getItem(bindingAdapterPosition).id
                }
            }

    }

    private class DiffCallback : DiffUtil.ItemCallback<Shopping>() {
        override fun areItemsTheSame(oldItem: Shopping, newItem: Shopping): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Shopping, newItem: Shopping): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_shopping, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = currentList[position]
        tracker?.let { holder.bind(item, it.isSelected(item.id), it.hasSelection()) }
    }
}