package com.estimewa.myapp.ui.shopping

import android.os.Bundle
import android.view.*
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estimewa.myapp.R
import com.estimewa.myapp.core.ui.ShoppingListAdapter
import com.estimewa.myapp.databinding.FragmentShoppingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment : Fragment() {

    private val viewModel: ShoppingViewModel by viewModels()
    private val listAdapter = ShoppingListAdapter()

    private var _binding: FragmentShoppingBinding? = null
    private val binding get() = _binding!!

    private var actionMode: ActionMode? = null
    private lateinit var selectionTracker: SelectionTracker<Long>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        _binding = FragmentShoppingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            initWidget()
            observeViewModel()
        }

    }

    private fun observeViewModel() {
        viewModel.dummy.observe(viewLifecycleOwner, {
            listAdapter.submitList(it)
        })
    }

    private fun initWidget() {
        with(binding) {
            with(rvShopping) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = listAdapter

                selectionTracker = SelectionTracker.Builder(
                    "shopping-selection-id",
                    this,
                    ShoppingItemKeyProvider(listAdapter),
                    ShoppingItemDetailsLookup(this),
                    StorageStrategy.createLongStorage()
                ).withSelectionPredicate(
                    SelectionPredicates.createSelectAnything()
                ).build()

                listAdapter.tracker = selectionTracker

                listAdapter.onItemClick = {
                    findNavController().navigate(R.id.action_navigation_shopping_to_navigation_shopping_detail)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shop_menu, menu)
    }
}

private class ShoppingItemDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        return if (view != null) {
            (recyclerView.getChildViewHolder(view) as ShoppingListAdapter.ListViewHolder)
                .getItemDetails()
        } else {
            null
        }
    }

}

private class ShoppingItemKeyProvider(private val listAdapter: ShoppingListAdapter) :
    ItemKeyProvider<Long>(SCOPE_CACHED) {
    override fun getKey(position: Int): Long {
        return listAdapter.currentList[position].id
    }

    override fun getPosition(key: Long): Int {
        return listAdapter.currentList.indexOfFirst { it.id == key }
    }


}
