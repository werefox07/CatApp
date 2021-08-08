package com.werefox.app_catlist.di

import com.werefox.app_catlist.internal.presentation.presenter.CatListPresenter
import com.werefox.core_domain.usecase.GetCatsUseCase
import com.werefox.core_presentation.scope.CatListPresenterScope
import dagger.Module
import dagger.Provides

@Module
class CatListPresenterModule {

    @CatListPresenterScope
    @Provides
    internal fun provideCatListPresenter(
        getCatsUseCase: GetCatsUseCase
    ): CatListPresenter {
        return CatListPresenter(
            getCatsUseCase
        )
    }
}