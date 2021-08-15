package com.werefox.core_domain.interactor

import io.reactivex.Single
import org.jetbrains.annotations.NotNull

abstract class SingleUseCase <Params, ReturnType> {

    /**
     * @param params use EmptyParams object if useCase without params
     */
    abstract fun buildUseCaseSingle(@NotNull params: Params): Single<ReturnType>

}