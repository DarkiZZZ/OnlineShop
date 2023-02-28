package ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DelegateAdapterItemDiffCallback : DiffUtil.ItemCallback<DelegateAdapterItem>() {

    override fun areItemsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean {
        return oldItem::class == newItem::class && oldItem.id() == newItem.id()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean {
        return oldItem.content() == newItem.content()
    }

    override fun getChangePayload(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Any {
        return oldItem.payload(newItem)
    }
}