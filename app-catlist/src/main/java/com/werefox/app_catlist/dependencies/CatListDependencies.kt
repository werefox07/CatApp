package com.werefox.app_catlist.dependencies

import com.werefox.core_domain.usecase.GetCatsUseCase

interface CatListDependencies {
    fun getCatsUseCase(): GetCatsUseCase
}