package ru.msokolov.onlineshop.navigation

import ru.msokolov.onlineshop.R.*
import ru.msokolov.onlineshop.page_one.presentation.navigation.PageOneCommandProvider
import javax.inject.Inject

class PageOneCommandProviderImpl @Inject constructor() : PageOneCommandProvider {

    override val toPageTwo: NavCommand =
        NavCommand(id.action_pageOneFragment_to_pageTwoFragment)
}