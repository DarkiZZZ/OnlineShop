package ru.msokolov.onlineshop.profile.presentation.navigation

import ru.msokolov.onlineshop.navigation.NavCommand

interface ProfileCommandProvider {
    val toSignInPage: NavCommand
}