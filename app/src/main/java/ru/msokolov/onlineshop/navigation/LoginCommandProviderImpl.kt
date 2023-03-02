package ru.msokolov.onlineshop.navigation

import ru.msokolov.onlineshop.R
import ru.msokolov.onlineshop.login.presentation.navigation.LoginCommandProvider
import javax.inject.Inject

class LoginCommandProviderImpl @Inject constructor() : LoginCommandProvider {

    override val toPageOne: NavCommand = NavCommand(R.id.action_loginFragment_to_pageOneFragment)
}