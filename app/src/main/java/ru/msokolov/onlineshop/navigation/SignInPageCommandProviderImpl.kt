package ru.msokolov.onlineshop.navigation

import ru.msokolov.onlineshop.R.*
import ru.msokolov.onlineshop.sign_in.presentation.navigation.SignInPageCommandProvider
import javax.inject.Inject

class SignInPageCommandProviderImpl @Inject constructor() : SignInPageCommandProvider{

    override val toPageOne: NavCommand = NavCommand(id.action_signInPageFragment_to_pageOneFragment)
    override val toLogin: NavCommand = NavCommand(id.action_signInPageFragment_to_loginFragment)
}