package ru.msokolov.onlineshop.page_one.presentation.ui.adapters.brand

import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.DelegateAdapterItem

data class BrandModel(
    val id: Int,
    val content: String
) : DelegateAdapterItem {

    override fun id(): Any {
        return id
    }

    override fun content(): Any {
        return ContentEntityContent(content = content)
    }

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is BrandModel) {
            if (content != other.content) {
                return ChangePayload.ContentChanged(other.content)
            }
        }
        return DelegateAdapterItem.Payloadable.None
    }

    inner class ContentEntityContent(val content: String) {
        override fun equals(other: Any?): Boolean {
            if (other is ContentEntityContent) {
                return content == other.content
            }
            return false
        }

        override fun hashCode(): Int {
            var result = content.hashCode()
            result = 31 * result + content.hashCode()
            return result
        }
    }

    sealed class ChangePayload : DelegateAdapterItem.Payloadable {
        data class ContentChanged(val content: String) : ChangePayload()
    }
}