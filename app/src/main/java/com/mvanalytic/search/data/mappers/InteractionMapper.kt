package com.mvanalytic.search.data.mappers

import com.mvanalytic.search.data.models.LocalInteraction
import com.mvanalytic.search.domian.models.InteractionModel

object InteractionMapper {

    fun LocalInteraction.toInteracionModel(): InteractionModel {
        return InteractionModel(
            id = this.id,
            descriptionEng = this.descriptionEng,
            descriptionSpa = this.descriptionSpa
        )
    }
}