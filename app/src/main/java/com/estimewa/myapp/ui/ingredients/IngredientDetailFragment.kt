package com.estimewa.myapp.ui.ingredients

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.estimewa.myapp.R
import com.estimewa.myapp.databinding.IngredientDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientDetailFragment : Fragment() {

    private lateinit var viewModel: IngredientDetailViewModel
    private var _binding: IngredientDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = IngredientDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(IngredientDetailViewModel::class.java)

        val ingredientId = arguments?.getLong("id")

        if (activity != null) observeData(ingredientId)
    }

    private fun observeData(ingredientId: Long?) {
        if (ingredientId != null) {
            viewModel.setId(ingredientId)

            viewModel.getIngredientDetail(ingredientId).observe(viewLifecycleOwner) {
                with(binding) {
                    tvName.text = it.name
                    tvPrice.text = it.price
                    tvAmount.text = it.amount.toString()
                    tvUnit.text = it.unit
                    tvSeller.text = it.seller
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.ingredient_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                startActivity(Intent(activity, EditIngredientActivity::class.java).apply {
                    putExtra("id", viewModel.ingredientId)
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}