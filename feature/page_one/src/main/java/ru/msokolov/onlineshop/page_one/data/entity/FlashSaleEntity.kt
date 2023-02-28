package ru.msokolov.onlineshop.page_one.data.entity

import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapterItem

data class FlashSaleEntity(
    val category : String,
    val name     : String,
    val price    : String,
    val discount : String,
    val imageUrl : String
) : DelegateAdapterItem {

    override fun id(): Any {
        return name
    }

    override fun content(): Any {
        return FlashSaleEntityContent(price = price, discount = discount)
    }

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is FlashSaleEntity){
            if (price != other.price){
                return ChangePayload.PriceChanged(other.price)
            }
            if (discount != other.discount){
                return ChangePayload.DiscountChanged(other.discount)
            }
        }
        //May be error here
        return DelegateAdapterItem.Payloadable.None
    }

    inner class FlashSaleEntityContent(val price: String, val discount: String){
        override fun equals(other: Any?): Boolean {
            if (other is FlashSaleEntityContent){
                return price == other.price && discount == other.discount
            }
            return false
        }

        override fun hashCode(): Int {
            var result = price.hashCode()
            result = 31 * result + price.hashCode()
            return result
        }
    }

    sealed class ChangePayload: DelegateAdapterItem.Payloadable{
        data class DiscountChanged(val discount: String): ChangePayload()
        data class PriceChanged(val price: String): ChangePayload()
    }
}
