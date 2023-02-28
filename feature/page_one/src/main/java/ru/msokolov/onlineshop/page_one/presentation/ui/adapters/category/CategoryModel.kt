package ru.msokolov.onlineshop.page_one.presentation.ui.adapters.category

import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapterItem

data class CategoryModel(
    val id: Int,
    val backgroundId: Int,
    val name: String
) : DelegateAdapterItem {

    override fun id(): Any {
        return id
    }

    override fun content(): Any {
        return CategoryEntityContent(backgroundId = backgroundId)
    }

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is CategoryModel) {
            if (backgroundId != other.backgroundId) {
                return ChangePayload.BackgroundChanged(other.backgroundId)
            }
        }
        return DelegateAdapterItem.Payloadable.None
    }

    inner class CategoryEntityContent(val backgroundId: Int) {
        override fun equals(other: Any?): Boolean {
            if (other is CategoryEntityContent) {
                return backgroundId == other.backgroundId
            }
            return false
        }

        override fun hashCode(): Int {
            var result = backgroundId.hashCode()
            result = 31 * result + backgroundId.hashCode()
            return result
        }
    }

    sealed class ChangePayload : DelegateAdapterItem.Payloadable {
        data class BackgroundChanged(val backgroundId: Int) : ChangePayload()
    }
}