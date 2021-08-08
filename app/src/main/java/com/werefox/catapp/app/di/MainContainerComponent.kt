package com.werefox.catapp.app.di

import com.werefox.catapp.app.dependencies.MainContainerDependencies
import com.werefox.catapp.app.internal.presentation.view.MainContainerActivity
import com.werefox.core_presentation.scope.MainContainerScope
import dagger.Component


@MainContainerScope
@Component(
    modules = [MainContainerModule::class],
    dependencies = [MainContainerDependencies::class]
)
interface MainContainerComponent {

    fun inject(mainContainerActivity: MainContainerActivity)
}