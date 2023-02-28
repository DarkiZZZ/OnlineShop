package ru.msokolov.onlineshop.page_one.data.entity

import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapterItem

data class LatestEntity(
    val category : String,
    val name     : String,
    val price    : String,
    val imageUrl : String
): DelegateAdapterItem {

    override fun id(): Any {
        return name
    }

    override fun content(): Any {
        return FlashSaleEntityContent(price = price)
    }

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is FlashSaleEntity) {
            if (price != other.price) {
                return ChangePayload.PriceChanged(other.price)
            }
        }
        return DelegateAdapterItem.Payloadable.None
    }

    inner class FlashSaleEntityContent(val price: String) {
        override fun equals(other: Any?): Boolean {
            if (other is FlashSaleEntityContent) {
                return price == other.price
            }
            return false
        }

        override fun hashCode(): Int {
            var result = price.hashCode()
            result = 31 * result + price.hashCode()
            return result
        }
    }

    sealed class ChangePayload : DelegateAdapterItem.Payloadable {
        data class PriceChanged(val price: String) : ChangePayload()
    }
}