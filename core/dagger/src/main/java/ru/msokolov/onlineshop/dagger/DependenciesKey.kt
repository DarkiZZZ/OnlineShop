package ru.msokolov.onlineshop.dagger

import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class DependenciesKey(val value: KClass<out Dependencies>)
