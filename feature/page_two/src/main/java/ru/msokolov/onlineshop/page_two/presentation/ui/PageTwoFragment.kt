package ru.msokolov.onlineshop.page_two.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import dagger.Lazy
import ru.msokolov.onlineshop.dagger.findDependencies
import ru.msokolov.onlineshop.network.Status
import ru.msokolov.onlineshop.page_two.R
import ru.msokolov.onlineshop.page_two.data.entity.DetailedInfoEntity
import ru.msokolov.onlineshop.page_two.databinding.FragmentPageTwoBinding
import ru.msokolov.onlineshop.page_two.di.DaggerPageTwoComponent
import ru.msokolov.onlineshop.page_two.presentation.navigation.PageTwoCommandProvider
import ru.msokolov.onlineshop.page_two.presentation.ui.gallery.GalleryAdapter
import ru.msokolov.onlineshop.page_two.presentation.ui.gallery.MainPhotoAdapter
import javax.inject.Inject


class PageTwoFragment : Fragment(R.layout.fragment_page_two) {

    @Inject
    lateinit var viewModelFactory: Lazy<PageTwoViewModel.Companion.PageTwoViewModelFactory>
    private val viewModel: PageTwoViewModel by viewModels { viewModelFactory.get() }

    @Inject
    lateinit var pageOneCommandProvider: PageTwoCommandProvider

    private lateinit var binding: FragmentPageTwoBinding

    private var adapterGallery: GalleryAdapter? = null
    private var adapterMainPhoto: MainPhotoAdapter? = null

    override fun onAttach(context: Context) {
        DaggerPageTwoComponent.builder()
            .pageTwoDependencies(findDependencies())
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapterGallery = GalleryAdapter()
        adapterMainPhoto = MainPhotoAdapter(requireContext())
        binding = FragmentPageTwoBinding.inflate(layoutInflater, container, false)
        setupMainPhotoAdapter()
        setupGalleryAdapter()
        return binding.root
    }

    override fun onDestroy() {
        adapterGallery = null
        adapterMainPhoto = null
        super.onDestroy()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.getData().collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        setupData((it.data as DetailedInfoEntity))
                    }
                    Status.ERROR -> Log.d(
                        TAG,
                        "error: ${it.message.toString()}"
                    )
                    Status.LOADING -> Log.d(
                        TAG,
                        it.message.toString()
                    )
                }
            }
        }
    }

    private fun setupMainPhotoAdapter() {
        with(binding.mainPhotoRecyclerView) {
            PagerSnapHelper().attachToRecyclerView(this)
            adapter = adapterMainPhoto
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupGalleryAdapter() {
        with(binding.galleryRecyclerView) {
            adapter = adapterGallery
            scrollToPosition(adapterGallery!!.start)
            setPadding(
                PADDING_HORIZON_ADAPTER,
                PADDING_VERTICAL_ADAPTER,
                PADDING_HORIZON_ADAPTER,
                PADDING_VERTICAL_ADAPTER
            )
            clipToPadding = false
        }
    }


    private fun setupData(detailedInfo: DetailedInfoEntity) {
        with(binding) {
            nameAndPriceLayout.productNameTextView.text = detailedInfo.name
            nameAndPriceLayout.priceTextView.text =
                getString(R.string.product_price, detailedInfo.price.toString())
            descriptionAndRatingLayout.descriptionTextView.text = detailedInfo.description
            Log.d("TAGRATING", "${detailedInfo.rating}")
            descriptionAndRatingLayout.ratingValueTextView.text =
                getString(R.string.rating_value, detailedInfo.rating)
            descriptionAndRatingLayout.reviewsAmountTextView.text =
                getString(R.string.reviews_value, detailedInfo.numberOfReviews.toString())

            adapterMainPhoto!!.setData(detailedInfo.imageUrlList)
            adapterGallery!!.setData(detailedInfo.imageUrlList)
        }
    }

    companion object{
        private const val TAG = "PAGE_TWO_TAG"
        private const val PADDING_HORIZON_ADAPTER = 100
        private const val PADDING_VERTICAL_ADAPTER = 50
    }

}