package com.estimewa.myapp.ui.recipe

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.estimewa.myapp.R
import com.estimewa.myapp.core.ui.RecipeListAdapter
import com.estimewa.myapp.databinding.FragmentRecipeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModels()
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    private val listAdapter = RecipeListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            initWidget()
            observeViewModel()
        }
    }

    private fun initWidget() {
        with(binding){
            with(rvRecipe){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = listAdapter
            }

            fabAddRecipe.setOnClickListener {
                startActivity(Intent(activity, EditRecipeActivity::class.java))
            }
        }
    }

    private fun observeViewModel() {
        viewModel.dummy.observe(viewLifecycleOwner, {
            listAdapter.submitList(it)
            listAdapter.onItemClick = {
                findNavController().navigate(R.id.action_navigation_recipe_to_navigation_recipe_detail)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}