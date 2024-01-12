package com.example.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.databinding.FragmentCatBinding
import com.example.presentation.util.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatFragment : Fragment(R.layout.fragment_cat) {
    private val vb: FragmentCatBinding by viewBinding()
    private val viewModel: CatViewModule by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb.catList.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    render(it)
                }
            }
        }
    }

    private fun render(uiState: CatViewModule.UIState) {
        when (uiState) {
            is CatViewModule.UIState.Error -> {

            }

            CatViewModule.UIState.Loading -> {
            }

            is CatViewModule.UIState.Success -> {
                vb.catList.adapter = CatsAdapter(uiState.data) {

                }
            }
        }
    }
}