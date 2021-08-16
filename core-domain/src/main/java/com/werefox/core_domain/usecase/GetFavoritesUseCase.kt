package com.werefox.core_domain.usecase

import com.werefox.core_domain.entity.CatFavoriteEntity
import com.werefox.core_domain.interactor.EmptyParams
import com.werefox.core_domain.interactor.SingleUseCase
import com.werefox.core_domain.repository.CatRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val catRepository: CatRepository) :
    SingleUseCase<EmptyParams, List<CatFavoriteEntity>>() {

    override fun buildUseCaseSingle(params: EmptyParams): Single<List<CatFavoriteEntity>> {
        return catRepository.getCatsFromFavourite()
    }
}