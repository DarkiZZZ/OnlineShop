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
import dagger.Lazy
import ru.msokolov.onlineshop.dagger.findDependencies
import ru.msokolov.onlineshop.navigation.navigate
import ru.msokolov.onlineshop.page_one.R
import ru.msokolov.onlineshop.page_one.data.entity.FlashSaleListEntity
import ru.msokolov.onlineshop.page_one.data.entity.LatestListEntity
import ru.msokolov.onlineshop.page_one.databinding.FragmentPageOneBinding
import ru.msokolov.onlineshop.page_one.di.DaggerPageOneComponent
import ru.msokolov.onlineshop.page_one.presentation.ui.adapters.latest.SaleAdapter
import javax.inject.Inject

class PageOneFragment : Fragment(R.layout.fragment_page_one) {

    private lateinit var binding: FragmentPageOneBinding

    @Inject
    lateinit var viewModelFactory : Lazy<PageOneViewModel.Companion.PageOneViewModelFactory>

    private val viewModel : PageOneViewModel by viewModels{ viewModelFactory.get() }

    @Inject
    lateinit var pageOneCommandProvider: PageOneCommandProvider

    private var adapterSale : SaleAdapter? = null

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
    ): View? {
        adapterSale = SaleAdapter(requireContext()) { navigate(pageOneCommandProvider.toPageTwo) }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            viewModel.getData().collect{
                when(it.status){
                    Status.SUCCESS -> {
                        when(it.data){
                            is LatestListEntity -> { Log.d("TAGTAGTAG", "latest: ${ it.data.latestList }")}
                            is FlashSaleListEntity -> {Log.d("TAGTAGTAG", "sale: ${ it.data.flashSaleList }")}
                        }
                    }
                    Status.ERROR -> Log.d("TAGTAGTAG", "error: ${it.message.toString()}")
                    Status.LOADING -> Log.d("TAGTAGTAG", it.message.toString())
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)

    }
}