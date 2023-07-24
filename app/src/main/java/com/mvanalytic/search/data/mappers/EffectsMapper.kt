package com.mvanalytic.search.data.mappers

import com.mvanalytic.search.data.models.LocalEffects
import com.mvanalytic.search.domian.models.EffectsModel

object EffectsMapper {

    fun LocalEffects.toEffectsModel(): EffectsModel {
        return EffectsModel(
            id = this.id,
            descriptionEng = this.descriptionEng,
            descriptionSpa = this.descriptionSpa
        )
    }
}