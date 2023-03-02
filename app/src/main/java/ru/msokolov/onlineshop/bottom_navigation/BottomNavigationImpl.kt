package ru.msokolov.onlineshop.bottom_navigation

import ru.msokolov.onlineshop.R
import ru.msokolov.onlineshop.navigation.NavCommand
import javax.inject.Inject

class BottomNavigationImpl @Inject constructor(): BottomNavigation {

    override val toProfile: NavCommand = NavCommand(R.id.action_feature_profile)
    override val toPageOne: NavCommand = NavCommand(R.id.action_feature_page_one)
}