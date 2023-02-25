package ru.msokolov.onlineshop.bottom_navigation

import ru.msokolov.onlineshop.R
import ru.msokolov.onlineshop.navigation.NavCommand

class BottomNavigationImpl: BottomNavigation {

    override val toProfile: NavCommand
        get() = NavCommand(R.id.action_feature_profile)
}