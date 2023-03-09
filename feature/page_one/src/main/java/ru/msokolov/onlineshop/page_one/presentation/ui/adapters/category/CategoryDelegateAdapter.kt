package ru.msokolov.onlineshop.page_one.presentation.ui.adapters.category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ru.msokolov.onlineshop.feature.page_one.databinding.CategoryItemBinding
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapter
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapterItem

class CategoryDelegateAdapter(private val context: Context) :
    DelegateAdapter<CategoryModel, CategoryDelegateAdapter.CategoryViewHolder>(CategoryModel::class.java) {

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryModel) {
            binding.nameTextView.text = item.name
            binding.imageView.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    item.backgroundId
                )
            )
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemBinding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemBinding)
    }

    override fun bindViewHolder(
        model: CategoryModel,
        viewHolder: CategoryDelegateAdapter.CategoryViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        when (val payload = payloads.firstOrNull() as? CategoryModel.ChangePayload) {
            is CategoryModel.ChangePayload.BackgroundChanged -> {}

            else -> viewHolder.bind(model)
        }
    }
}