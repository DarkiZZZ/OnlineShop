package ru.msokolov.onlineshop.bottom_navigation

import ru.msokolov.onlineshop.navigation.NavCommand

interface BottomNavigation{
    val toProfile: NavCommand
    val toPageOne: NavCommand
}