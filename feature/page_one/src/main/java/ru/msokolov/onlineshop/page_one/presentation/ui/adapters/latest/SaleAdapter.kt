package ru.msokolov.onlineshop.page_one.presentation.ui.adapters.latest

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.msokolov.onlineshop.page_one.databinding.FlashSaleItemBinding

class SaleAdapter(private val context: Context, private val navigateToPageTwo: () -> Unit) :
    RecyclerView.Adapter<SaleAdapter.SaleViewHolder>() {

    inner class SaleViewHolder(private val binding: FlashSaleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /*fun bind()*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}