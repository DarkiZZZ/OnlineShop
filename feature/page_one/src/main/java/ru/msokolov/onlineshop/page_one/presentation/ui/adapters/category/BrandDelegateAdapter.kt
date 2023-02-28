package ru.msokolov.onlineshop.page_one.presentation.ui.adapters.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.msokolov.onlineshop.page_one.databinding.BrandItemBinding
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapter
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapterItem

class BrandDelegateAdapter :
    DelegateAdapter<BrandModel, BrandDelegateAdapter.BrandViewHolder>(BrandModel::class.java) {

    inner class BrandViewHolder(private val binding: BrandItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BrandModel) {
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemBinding =
            BrandItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(itemBinding)
    }

    override fun bindViewHolder(
        model: BrandModel,
        viewHolder: BrandDelegateAdapter.BrandViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        when (val payload = payloads.firstOrNull() as? BrandModel.ChangePayload) {
            is BrandModel.ChangePayload.ContentChanged -> {}

            else -> viewHolder.bind(model)
        }
    }
}