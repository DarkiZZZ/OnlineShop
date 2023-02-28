package ru.msokolov.onlineshop.page_one.presentation.navigation

import ru.msokolov.onlineshop.navigation.NavCommand

interface PageOneCommandProvider {
    val toPageTwo: NavCommand
}