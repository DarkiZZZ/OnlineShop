package ru.msokolov.onlineshop.page_two.presentation.ui.gallery

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.msokolov.onlineshop.feature.page_two.R
import ru.msokolov.onlineshop.feature.page_two.databinding.MainPhotoItemBinding


class MainPhotoAdapter(private val context: Context) :
    RecyclerView.Adapter<MainPhotoAdapter.MainPhotoViewHolder>() {

    private var allData: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPhotoViewHolder {
        val viewBinding =
            MainPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainPhotoViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MainPhotoViewHolder, position: Int) {
        val currentItem = allData[position]
        holder.bind(currentItem)

    }

    override fun getItemCount(): Int = allData.size


    inner class MainPhotoViewHolder(private val binding: MainPhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            binding.shareButton.setOnClickListener {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, url);
                context.startActivity(
                    Intent.createChooser(
                        shareIntent,
                        context.getString(R.string.share_to)
                    )
                )
            }
            Picasso.get()
                .load(url)
                .resize(600, 600)
                .into(binding.mainPhotoImageView)

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(PhotoUrlsList: List<String>) {
        allData = PhotoUrlsList
        notifyDataSetChanged()
    }
}