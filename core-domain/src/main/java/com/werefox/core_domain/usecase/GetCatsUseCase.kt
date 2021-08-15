package com.werefox.core_domain.usecase

import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.interactor.EmptyParams
import com.werefox.core_domain.interactor.SingleUseCase
import com.werefox.core_domain.repository.CatRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private val catRepository: CatRepository
) : SingleUseCase<EmptyParams, List<CatEntity>>() {

    override fun buildUseCaseSingle(params: EmptyParams): Single<List<CatEntity>> {
        return catRepository.getRemoteCats()
    }
}
