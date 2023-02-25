package ru.msokolov.onlineshop.dagger

interface Dependencies

interface HasDependencies{
    val dependenciesMap: Map<Class<out Dependencies>, @JvmSuppressWildcards Dependencies>
}