package com.mvanalytic.search.domian.repositories

import com.mvanalytic.search.domian.models.MedModel

interface MedsRepository {

    fun getMeds(): List<MedModel>
}