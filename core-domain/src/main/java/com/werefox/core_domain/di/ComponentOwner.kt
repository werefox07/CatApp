package com.werefox.core_domain.di

interface ComponentOwner<T> {

    fun provideComponent(): T

    fun inject(t: T)
}