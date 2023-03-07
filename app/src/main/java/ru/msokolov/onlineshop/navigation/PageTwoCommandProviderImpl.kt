package ru.msokolov.onlineshop.navigation

import ru.msokolov.onlineshop.R.*
import ru.msokolov.onlineshop.page_two.presentation.navigation.PageTwoCommandProvider
import javax.inject.Inject

class PageTwoCommandProviderImpl @Inject constructor() : PageTwoCommandProvider {
    override val toPageOne: NavCommand = NavCommand(id.action_pageTwoFragment_to_pageOneFragment)
    override val toCart: NavCommand = NavCommand(id.action_pageTwoFragment_to_cartFragment)

}