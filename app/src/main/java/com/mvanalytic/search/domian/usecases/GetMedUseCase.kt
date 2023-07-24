package com.mvanalytic.search.domian.usecases

import com.mvanalytic.search.domian.models.MedModel
import com.mvanalytic.search.domian.repositories.MedsRepository

class GetMedUseCase(
    private val medsRepository: MedsRepository
) {

    fun execute(): List<MedModel> {
        return medsRepository.getMeds()
    }
}