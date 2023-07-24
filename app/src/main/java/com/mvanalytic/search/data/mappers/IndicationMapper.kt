package com.mvanalytic.search.data.mappers

import com.mvanalytic.search.data.models.LocalIndication
import com.mvanalytic.search.domian.models.IndicationModel

object IndicationMapper {

    fun LocalIndication.toIndicationModel(): IndicationModel {
        return IndicationModel(
            id = this.id,
            descriptionEng = this.descriptionEng,
            descriptionSpa = this.descriptionSpa
        )
    }
}