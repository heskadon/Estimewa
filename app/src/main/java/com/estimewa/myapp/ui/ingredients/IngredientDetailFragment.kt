package com.estimewa.myapp.ui.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
            viewModel.getIngredientDetail(ingredientId).observe(viewLifecycleOwner, {
                with(binding){
                    tvDummyText.text = it.toString()
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}