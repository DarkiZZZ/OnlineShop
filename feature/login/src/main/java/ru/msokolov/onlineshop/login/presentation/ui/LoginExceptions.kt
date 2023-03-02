package ru.msokolov.onlineshop.login.presentation.ui

import ru.msokolov.onlineshop.livedata.AppException

class EmptyFieldException(
    val field: LoginField
) : AppException()

class AccountAlreadyExistsException() : AppException()