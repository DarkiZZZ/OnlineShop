package ru.msokolov.onlineshop.page_two.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import dagger.Lazy
import ru.msokolov.onlineshop.dagger.findDependencies
import ru.msokolov.onlineshop.livedata.observeEvent
import ru.msokolov.onlineshop.navigation.navigate
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
    lateinit var pageTwoCommandProvider: PageTwoCommandProvider

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
        observeDataFromViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapterGallery = GalleryAdapter()
        adapterMainPhoto = MainPhotoAdapter(requireContext())
        binding = FragmentPageTwoBinding.inflate(layoutInflater, container, false)
        observeProductPrice()
        observeEvent()
        setupMainPhotoAdapter()
        setupGalleryAdapter()
        setupClickListeners()
        return binding.root
    }

    override fun onDestroy() {
        adapterGallery = null
        adapterMainPhoto = null
        super.onDestroy()
    }

    @Suppress("DEPRECATION")
    private fun observeDataFromViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.getData().collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        setupData((it.data as DetailedInfoEntity))
                    }
                    Status.ERROR -> {}
                    Status.LOADING -> {}
                }
            }
        }
    }

    private fun observeProductPrice() {
        viewModel.currentPriceSum.observe(viewLifecycleOwner) {
            binding.productSumTextView.text = getString(R.string.general_sum_value, it.toString())
        }
    }

    private fun observeEvent() {
        viewModel.ableGoToCartEvent.observeEvent(viewLifecycleOwner) {
            navigate(pageTwoCommandProvider.toCart)
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
        viewModel.setupProductPrice(detailedInfo.price)
        with(binding) {
            with(nameAndPriceLayout) {
                productNameTextView.text = detailedInfo.name
                priceTextView.text = getString(R.string.product_price, detailedInfo.price.toString())
            }
            with(descriptionAndRatingLayout){
                descriptionTextView.text = detailedInfo.description
                ratingValueTextView.text = getString(R.string.rating_value, detailedInfo.rating)
                reviewsAmountTextView.text = getString(R.string.reviews_value, detailedInfo.numberOfReviews.toString())
            }
            setupColors(colors = detailedInfo.colorList)
            adapterMainPhoto!!.setData(detailedInfo.imageUrlList)
            adapterGallery!!.setData(detailedInfo.imageUrlList)
        }
    }


    private fun setupColors(colors: List<String>) {
        with(binding.colorsLayout) {
            colorOne.setBackgroundColor(colors[0].toColorInt())
            colorTwo.setBackgroundColor(colors[1].toColorInt())
            colorThree.setBackgroundColor(colors[2].toColorInt())
        }
    }

    private fun setupClickListeners() {
        binding.addToCartTextView.setOnClickListener {
            viewModel.goToChart()
        }
        binding.plusButton.setOnClickListener {
            viewModel.increaseProductAmount()
        }
        binding.minusButton.setOnClickListener {
            viewModel.decreaseProductAmount()
        }
    }

    companion object {
        private const val TAG = "PAGE_TWO_TAG"
        private const val PADDING_HORIZON_ADAPTER = 100
        private const val PADDING_VERTICAL_ADAPTER = 50
    }

}