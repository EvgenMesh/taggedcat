package com.example.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.CatItemBinding
import com.example.presentation.model.CatUIModel

class CatsAdapter(private val items: List<CatUIModel>, private val itemClickListener : (CatUIModel) -> Unit) : RecyclerView.Adapter<CatsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    class ViewHolder(private val binding: CatItemBinding, itemClickListener: (CatUIModel) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        private var currentModel : CatUIModel? = null

        init {
            binding.root.setOnClickListener {
                currentModel?.let {
                    itemClickListener.invoke(it)
                }
            }
        }

        fun onBind(model: CatUIModel) {
            currentModel = model
            binding.title.text = model.title
        }
    }
}