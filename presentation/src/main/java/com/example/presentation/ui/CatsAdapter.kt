package com.example.presentation.ui

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.databinding.CatItemBinding
import com.example.presentation.model.CatUIModel

class CatsAdapter(private val itemClickListener : (CatUIModel) -> Unit) : RecyclerView.Adapter<CatsAdapter.ViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<CatUIModel>() {
        override fun areItemsTheSame(
            oldItem: CatUIModel,
            newItem: CatUIModel
        ): Boolean {
            return oldItem.published == newItem.published
        }

        override fun areContentsTheSame(
            oldItem: CatUIModel,
            newItem: CatUIModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    fun saveData(dataResponse: List<CatUIModel>) {
        asyncListDiffer.submitList(dataResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, itemClickListener)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(asyncListDiffer.currentList[position])
    }

    class ViewHolder(private val vb: CatItemBinding, itemClickListener: (CatUIModel) -> Unit) : RecyclerView.ViewHolder(vb.root) {

        private var currentModel : CatUIModel? = null

        init {
            vb.root.setOnClickListener {
                currentModel?.let {
                    itemClickListener.invoke(it)
                }
            }
        }

        fun onBind(model: CatUIModel) {
            currentModel = model
            vb.title.text = Html.fromHtml(model.description, Html.FROM_HTML_MODE_LEGACY)
            Glide
                .with(vb.root.context)
                .load(model.mediaUrl)
                .centerCrop()
                .into(vb.image)
        }
    }
}