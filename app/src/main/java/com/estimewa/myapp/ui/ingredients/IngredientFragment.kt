package com.estimewa.myapp.ui.ingredients

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.estimewa.myapp.core.data.Resource
import com.estimewa.myapp.core.ui.IngredientPagingAdapter
import com.estimewa.myapp.databinding.FragmentIngredientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class IngredientFragment : Fragment() {

    private val viewModel: IngredientViewModel by viewModels()
    private var _binding: FragmentIngredientBinding? = null
    private val binding get() = _binding!!
    private val pagedAdapter = IngredientPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            with(binding) {
                fabEdit.setOnClickListener {
                    startActivity(Intent(activity, EditIngredientActivity::class.java))
                }

                with(rvIngredients) {
                    adapter = pagedAdapter
                }
            }
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.ingredientList.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> Timber.d("Resource Loading")
                is Resource.Success -> {
                    /*listAdapter.submitList(resource.data)

                    listAdapter.onItemClick = {
                        val bundle = bundleOf("id" to it.ingredientId)

                        findNavController().navigate(
                            R.id.action_navigation_ingredient_to_navigation_ingredient_detail,
                            bundle
                        )
                    }*/
                }
                is Resource.Error -> {
                    Timber.e("Resource Error : ${resource.message}")
                }
                else -> Timber.e("Exception")
            }
        }

        /*viewModel.pagedIngredient.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch {
                Timber.d("pagedIngredient : $pagingData")
                pagedAdapter.submitData(pagingData)
            }
        }*/
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ingredients.collectLatest(pagedAdapter::submitData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvIngredients.adapter = null
        _binding = null
    }
}
