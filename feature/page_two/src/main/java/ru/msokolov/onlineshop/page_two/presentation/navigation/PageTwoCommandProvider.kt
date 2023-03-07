package ru.msokolov.onlineshop.page_two.presentation.navigation

import ru.msokolov.onlineshop.navigation.NavCommand

interface PageTwoCommandProvider {
    val toPageTwo: NavCommand
    val toCart: NavCommand
}