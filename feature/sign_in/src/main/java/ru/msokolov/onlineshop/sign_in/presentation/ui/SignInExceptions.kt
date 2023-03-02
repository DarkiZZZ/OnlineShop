package ru.msokolov.onlineshop.sign_in.presentation.ui

import ru.msokolov.onlineshop.livedata.AppException


class EmptyFieldException(
    val field: SignInField
) : AppException()

class EmailIsNotProperException() : AppException()