package com.mvanalytic.search.data.repositories

import com.mvanalytic.search.data.datasources.LocalMedsDataSource
import com.mvanalytic.search.data.mappers.MedMapper.toMedModel
import com.mvanalytic.search.domian.models.MedModel
import com.mvanalytic.search.domian.repositories.MedsRepository

class MedsRepositoryImpl(
    private val localMeds: LocalMedsDataSource
): MedsRepository {


    override fun getMeds(): List<MedModel> {
        return localMeds.getMeds()
            .map { item -> item.toMedModel() }
    }

}