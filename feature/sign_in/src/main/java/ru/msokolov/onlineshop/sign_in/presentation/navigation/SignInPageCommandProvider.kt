package ru.msokolov.onlineshop.sign_in.presentation.navigation

import ru.msokolov.onlineshop.navigation.NavCommand

interface SignInPageCommandProvider {
    val toPageOne: NavCommand
    val toLogin: NavCommand
}