package com.werefox.core_domain.manager

interface ComponentOwner<T> {

    fun provideComponent(): T

    fun inject(t: T)
}