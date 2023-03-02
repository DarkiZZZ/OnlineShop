package ru.msokolov.onlineshop.login.presentation.navigation

import ru.msokolov.onlineshop.navigation.NavCommand

interface LoginCommandProvider {
    val toPageOne: NavCommand
}