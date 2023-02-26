package ru.msokolov.onlineshop.page_one.presentation.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.Lazy
import ru.msokolov.onlineshop.page_one.R
import ru.msokolov.onlineshop.page_one.databinding.FragmentPageOneBinding
import javax.inject.Inject

class PageOneFragment : Fragment(R.layout.fragment_page_one) {

    private lateinit var binding: FragmentPageOneBinding

    @Inject
    lateinit var viewModelFactory : Lazy<PageOneViewModel.Companion.PageOneViewModelFactory>

    private val viewModel : PageOneViewModel by viewModels{ viewModelFactory.get() }
}