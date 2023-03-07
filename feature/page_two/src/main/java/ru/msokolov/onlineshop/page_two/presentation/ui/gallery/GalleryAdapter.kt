package ru.msokolov.onlineshop.page_two.presentation.ui.gallery

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.msokolov.onlineshop.page_two.databinding.GalleryItemBinding

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private var allData: List<String> = listOf(EMPTY_ITEM, EMPTY_ITEM, EMPTY_ITEM)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val viewBinding = GalleryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val index = position % allData.size
        val currentItem = allData[index]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    val start: Int
        get() {
            val temp = itemCount / 2
            return temp - temp % allData.size
        }


    inner class GalleryViewHolder(private val binding: GalleryItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(url: String){
            if (url == EMPTY_ITEM) return
            else {
                Picasso.get()
                    .load(url)
                    .resize(200, 200)
                    .into(binding.photoImageView)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(PhotoUrlsList : List<String>){
        allData = PhotoUrlsList
        notifyDataSetChanged()
    }

    companion object{
        private const val EMPTY_ITEM = "null"
    }
}