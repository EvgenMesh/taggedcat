package com.example.main.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.di.ComponentProvider
import com.example.main.R
import com.example.main.databinding.FragmentCatBinding
import com.example.main.di.CatsDepsProvider
import com.example.presentation.util.viewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatFragment : Fragment(R.layout.fragment_cat) {

    private val vb: FragmentCatBinding by viewBinding()
    private var infoView: Snackbar? = null

    private lateinit var viewModel: CatViewModel

    @Inject
    lateinit var mainViewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        ((activity?.application as? ComponentProvider)?.featureComponent() as? CatsDepsProvider)?.injectCatsFragment(
            this
        )
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, mainViewModelFactory)[CatViewModel::class.java]

        vb.catList.layoutManager =
            GridLayoutManager(requireContext(), resources.getInteger(R.integer.grid_column_count))
        vb.catList.adapter = CatsAdapter {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(it.link)
                )
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    render(it)
                }
            }
        }
    }

    private fun render(uiState: CatViewModel.UIState) {
        when (uiState) {
            is CatViewModel.UIState.Error -> {
                vb.loading.visibility = View.GONE
                showInfo(getString(R.string.error_info), uiState.retryClick)
            }

            CatViewModel.UIState.Loading -> {
                vb.loading.visibility = View.VISIBLE
            }

            is CatViewModel.UIState.Success -> {
                vb.loading.visibility = View.GONE
                if (uiState.data.isEmpty()) {
                    infoView = Snackbar.make(
                        vb.root,
                        getString(R.string.empty_data),
                        Snackbar.LENGTH_INDEFINITE
                    ).apply {
                        this.setAction(R.string.retry) {
                            uiState.retryClick.invoke()
                        }.show()
                    }
                } else {
                    infoView?.dismiss()
                    (vb.catList.adapter as CatsAdapter).saveData(uiState.data)
                }
            }
        }
    }

    private fun showInfo(info: String, retryClick: () -> Unit) {
        Snackbar.make(vb.root, info, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry) {
                retryClick.invoke()
            }.show()
    }
}