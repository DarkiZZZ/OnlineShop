package ru.msokolov.onlineshop.appbar_navigation

import ru.msokolov.onlineshop.R
import ru.msokolov.onlineshop.navigation.NavCommand
import javax.inject.Inject

class AppBarNavigationImpl @Inject constructor() : AppBarNavigation {
    override val fromProfileToPageOne = NavCommand(R.id.action_profileFragment_to_pageOneFragment)
}