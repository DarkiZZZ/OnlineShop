package ru.msokolov.onlineshop.sign_in.presentation.ui

open class AppException : RuntimeException()

class EmptyFieldException(
    val field: SignInField
) : AppException()

class EmailIsNotProperException() : AppException()