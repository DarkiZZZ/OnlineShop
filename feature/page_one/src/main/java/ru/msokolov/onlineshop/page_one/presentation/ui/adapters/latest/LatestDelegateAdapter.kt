package ru.msokolov.onlineshop.page_one.presentation.ui.adapters.latest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.msokolov.onlineshop.feature.page_one.databinding.LatestItemBinding
import ru.msokolov.onlineshop.page_one.data.entity.latest.LatestEntity
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapter
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapterItem

class LatestDelegateAdapter :
    DelegateAdapter<LatestEntity, LatestDelegateAdapter.LatestViewHolder>(LatestEntity::class.java) {

    inner class LatestViewHolder(private val binding: LatestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LatestEntity) {
            binding.itemCostTextView.text = item.price
            binding.itemNameTextView.text = item.name
            binding.categoryTextView.text = item.category
            Picasso.get()
                .load(item.imageUrl)
                .into(binding.backgroundImageView)
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemBinding =
            LatestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LatestViewHolder(itemBinding)
    }

    override fun bindViewHolder(
        model: LatestEntity,
        viewHolder: LatestViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        when (val payload = payloads.firstOrNull() as? LatestEntity.ChangePayload) {
            is LatestEntity.ChangePayload.PriceChanged -> {}

            else -> viewHolder.bind(model)
        }
    }
}