package ru.msokolov.onlineshop.page_one.presentation.ui.adapters.sale

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.msokolov.onlineshop.page_one.data.entity.FlashSaleEntity
import ru.msokolov.onlineshop.page_one.databinding.FlashSaleItemBinding
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapter
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapterItem

class FlashSaleDelegateAdapter(private val navigateToPageTwo: () -> Unit) :
    DelegateAdapter<FlashSaleEntity, FlashSaleDelegateAdapter.FlashSaleViewHolder>(FlashSaleEntity::class.java) {

    inner class FlashSaleViewHolder(private val binding: FlashSaleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FlashSaleEntity) {
            binding.itemCostTextView.text = "$ ${item.price}"
            binding.itemNameTextView.text = item.name
            binding.categoryTextView.text = item.category
            binding.discountTextView.text = "${item.discount}% off"
            binding.backgroundImageView.setOnClickListener {
                navigateToPageTwo()
            }
            Picasso.get()
                .load(item.imageUrl)
                .into(binding.backgroundImageView)
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemBinding =
            FlashSaleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlashSaleViewHolder(itemBinding)
    }

    override fun bindViewHolder(
        model: FlashSaleEntity,
        viewHolder: FlashSaleViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        when (val payload = payloads.firstOrNull() as? FlashSaleEntity.ChangePayload) {
            is FlashSaleEntity.ChangePayload.PriceChanged -> {}
            is FlashSaleEntity.ChangePayload.DiscountChanged -> {}

            else -> viewHolder.bind(model)
        }
    }
}