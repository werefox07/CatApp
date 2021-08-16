package com.werefox.catapp.app.di

import com.werefox.catapp.app.coordinator.MainPartCoordinator
import com.werefox.catapp.app.internal.presentation.presenter.MainContainerPresenter
import com.werefox.catapp.app.output.MainContainerOutput
import dagger.Module
import dagger.Provides

@Module
class MainContainerModule {

    @MainContainerScope
    @Provides
    internal fun provideMainContainerPresenter(mainContainerOutput: MainContainerOutput): MainContainerPresenter {
        return MainContainerPresenter(mainContainerOutput)
    }

    @MainContainerScope
    @Provides
    internal fun mainContainerOutput(
        coordinator: MainPartCoordinator,
    ): MainContainerOutput =
        coordinator.mainContainerOutput
}