package com.werefox.core_domain.usecase

import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.interactor.CompletableUseCase
import com.werefox.core_domain.repository.CatRepository
import io.reactivex.Completable
import javax.inject.Inject

class SaveImageUseCase @Inject constructor(private val catRepository: CatRepository) :
    CompletableUseCase<Pair<CatEntity, String>>() {

    override fun buildUseCaseCompletable(params: Pair<CatEntity, String>): Completable {
        return catRepository.addCatToFavourite(params.first, params.second)
    }
}