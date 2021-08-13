package com.werefox.core_domain.usecase

import com.werefox.core_domain.entity.Cat
import com.werefox.core_domain.interactor.EmptyParams
import com.werefox.core_domain.interactor.ObservableUseCase
import com.werefox.core_domain.repository.CatRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private val catRepository: CatRepository
) : ObservableUseCase<EmptyParams, List<Cat>>() {

    override fun buildUseCaseObservable(emptyParams: EmptyParams): Observable<List<Cat>> {
        return catRepository.getRemoteCats()
    }
}
