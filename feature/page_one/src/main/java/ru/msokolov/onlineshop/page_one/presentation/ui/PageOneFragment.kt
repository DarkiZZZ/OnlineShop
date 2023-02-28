package ru.msokolov.onlineshop.page_one.presentation.ui

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
import dagger.Lazy
import ru.msokolov.onlineshop.dagger.findDependencies
import ru.msokolov.onlineshop.navigation.navigate
import ru.msokolov.onlineshop.page_one.R
import ru.msokolov.onlineshop.page_one.data.entity.FlashSaleListEntity
import ru.msokolov.onlineshop.page_one.data.entity.LatestListEntity
import ru.msokolov.onlineshop.page_one.databinding.FragmentPageOneBinding
import ru.msokolov.onlineshop.page_one.di.DaggerPageOneComponent
import ru.msokolov.onlineshop.page_one.presentation.navigation.PageOneCommandProvider
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.category.BrandDelegateAdapter
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.category.BrandModel
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.delegate.CompositeAdapter
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.sale.FlashSaleDelegateAdapter
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.latest.LatestDelegateAdapter
import javax.inject.Inject

class PageOneFragment : Fragment(R.layout.fragment_page_one) {

    private lateinit var binding: FragmentPageOneBinding

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


    @Inject
    lateinit var viewModelFactory: Lazy<PageOneViewModel.Companion.PageOneViewModelFactory>
    private val viewModel: PageOneViewModel by viewModels { viewModelFactory.get() }

    @Inject
    lateinit var pageOneCommandProvider: PageOneCommandProvider

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
        with(binding.flashSaleItemsRecyclerView) {
            adapter = flashSaleCompositeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        with(binding.latestItemsRecyclerView) {
            adapter = latestCompositeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
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
        lifecycleScope.launchWhenStarted {
            viewModel.getData().collect {
                when (it.status) {
                    ru.msokolov.onlineshop.network.Status.SUCCESS -> {
                        when (it.data) {
                            is LatestListEntity -> {
                                latestCompositeAdapter.submitList((it.data as LatestListEntity).latestList)
                            }
                            is FlashSaleListEntity -> {
                                flashSaleCompositeAdapter.submitList((it.data as FlashSaleListEntity).flashSaleList)
                            }
                        }
                    }
                    ru.msokolov.onlineshop.network.Status.ERROR -> Log.d(
                        "TAGTAGTAG",
                        "error: ${it.message.toString()}"
                    )
                    ru.msokolov.onlineshop.network.Status.LOADING -> Log.d(
                        "TAGTAGTAG",
                        it.message.toString()
                    )
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)

    }
}