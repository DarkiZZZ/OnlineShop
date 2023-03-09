package ru.msokolov.onlineshop.page_one.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Lazy
import ru.msokolov.onlineshop.dagger.findDependencies
import ru.msokolov.onlineshop.feature.page_one.R
import ru.msokolov.onlineshop.feature.page_one.databinding.FragmentPageOneBinding
import ru.msokolov.onlineshop.navigation.navigate
import ru.msokolov.onlineshop.network.Status.*
import ru.msokolov.onlineshop.page_one.data.entity.latest.LatestListEntity
import ru.msokolov.onlineshop.page_one.data.entity.sale.FlashSaleListEntity
import ru.msokolov.onlineshop.page_one.di.DaggerPageOneComponent
import ru.msokolov.onlineshop.page_one.presentation.navigation.PageOneCommandProvider
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.brand.BrandDelegateAdapter
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.brand.BrandModel
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.category.CategoryDelegateAdapter
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.category.CategoryModel
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.CompositeAdapter
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.latest.LatestDelegateAdapter
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.sale.FlashSaleDelegateAdapter
import java.util.*
import javax.inject.Inject

class PageOneFragment : Fragment(R.layout.fragment_page_one) {

    @Inject
    lateinit var viewModelFactory: Lazy<PageOneViewModel.Companion.PageOneViewModelFactory>
    private val viewModel: PageOneViewModel by viewModels { viewModelFactory.get() }

    @Inject
    lateinit var pageOneCommandProvider: PageOneCommandProvider

    private lateinit var binding: FragmentPageOneBinding

    private lateinit var wordsSearchingTimer: Timer

    private var wordsSearchingAdapter: ArrayAdapter<String>? = null

    private val flashSaleCompositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(FlashSaleDelegateAdapter { navigate(pageOneCommandProvider.toPageTwo) })
            .build()
    }
    private val latestCompositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(LatestDelegateAdapter())
            .build()
    }
    private val brandCompositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(BrandDelegateAdapter())
            .build()
    }
    private val categoryCompositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(CategoryDelegateAdapter(requireContext()))
            .build()
    }

    override fun onAttach(context: Context) {
        DaggerPageOneComponent.builder()
            .pageOneDependencies(findDependencies())
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageOneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupFlashSaleRecyclerView()
        setupLatestRecyclerView()
        setupBrandRecyclerView()
        setupCategoryRecyclerView()
        setupDataFromViewModel()
        setupSearchAutoFill()
        observeSearchWords()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        wordsSearchingAdapter = null
        super.onDestroy()
    }

    private fun setupFlashSaleRecyclerView() {
        with(binding.flashSaleItemsRecyclerView) {
            adapter = flashSaleCompositeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupCategoryRecyclerView() {
        with(binding.categoryRecView) {
            adapter = categoryCompositeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        val categoryList =
            listOf(
                CategoryModel(0, R.drawable.ic_phones, "Phones"),
                CategoryModel(1, R.drawable.ic_headphones, "Headphones"),
                CategoryModel(2, R.drawable.ic_games, "Games"),
                CategoryModel(3, R.drawable.ic_cars, "Cars"),
                CategoryModel(4, R.drawable.ic_furniture, "Furniture"),
                CategoryModel(5, R.drawable.ic_kids, "Kids")
            )
        categoryCompositeAdapter.submitList(categoryList)
    }

    private fun setupLatestRecyclerView() {
        with(binding.latestItemsRecyclerView) {
            adapter = latestCompositeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupBrandRecyclerView() {
        with(binding.brandItemsRecyclerView) {
            adapter = brandCompositeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        val brandList =
            listOf(
                BrandModel(0, "content0"),
                BrandModel(1, "content1"),
                BrandModel(2, "content2"),
                BrandModel(3, "content3"),
                BrandModel(4, "content4"),
            )
        brandCompositeAdapter.submitList(brandList)
    }

    private fun setupSearchAutoFill() {
        wordsSearchingTimer = Timer()
        wordsSearchingAdapter =
            ArrayAdapter(requireContext(), R.layout.seach_view_hint_item, arrayListOf<String>())
        with(binding.searchLayout.searchAutoFillTextView) {
            setAdapter(wordsSearchingAdapter)
            doAfterTextChanged {
                wordsSearchingTimer.cancel()
                wordsSearchingTimer = Timer()
                wordsSearchingTimer.schedule(
                    object : TimerTask() {
                        override fun run() {
                            viewModel.getSearchWords()
                        }
                    },
                    SEARCH_WORDS_DELAY
                )
            }
        }
    }

    private fun handingWordsSearchingAdapterData(words: List<String>) {
        wordsSearchingAdapter!!.clear()
        for (word in words) {
            wordsSearchingAdapter!!.add(word)
        }
        wordsSearchingAdapter!!.filter.filter(
            binding.searchLayout.searchAutoFillTextView.text,
            null
        )
    }

    private fun observeSearchWords() {
        viewModel.searchWordsList.observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    handingWordsSearchingAdapterData(it.data!!.words)
                }
                ERROR -> {}
                LOADING -> {}
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun setupDataFromViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.getData().collect {
                when (it.status) {
                    SUCCESS -> {
                        when (it.data) {
                            is LatestListEntity -> {
                                latestCompositeAdapter.submitList((it.data as LatestListEntity).latestList)
                            }
                            is FlashSaleListEntity -> {
                                flashSaleCompositeAdapter.submitList((it.data as FlashSaleListEntity).flashSaleList)
                            }
                        }
                    }
                    ERROR -> {}
                    LOADING -> {}
                }
            }
        }
    }

    companion object {
        private const val SEARCH_WORDS_DELAY = 1000L
    }
}