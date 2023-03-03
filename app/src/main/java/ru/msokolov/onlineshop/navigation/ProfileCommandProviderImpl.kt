package ru.msokolov.onlineshop.navigation

import ru.msokolov.onlineshop.R.*
import ru.msokolov.onlineshop.profile.presentation.navigation.ProfileCommandProvider
import javax.inject.Inject

class ProfileCommandProviderImpl @Inject constructor(): ProfileCommandProvider {
    override val toSignInPage: NavCommand = NavCommand(id.action_profileFragment_to_signInPageFragment)
}