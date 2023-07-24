package com.mvanalytic.search.data.mappers

import com.mvanalytic.search.data.mappers.EffectsMapper.toEffectsModel
import com.mvanalytic.search.data.mappers.IndicationMapper.toIndicationModel
import com.mvanalytic.search.data.mappers.InteractionMapper.toInteracionModel
import com.mvanalytic.search.data.models.LocalMed
import com.mvanalytic.search.domian.models.MedModel

object MedMapper {

    fun LocalMed.toMedModel(): MedModel {
        return MedModel(
            id = this.id,
            nameEng = this.nameEng,
            nameSpa = this.nameSpa,
            brand = this.brand,
            dose = this.dose,
            descriptionEng = this.descriptionEng,
            descriptionSpa = this.descriptionSpa,
            routeEng = this.routeEng,
            routeSpa = this.routeSpa,
            photoSmall = this.photoSmall,
            photoMedium = this.photoMedium,
            photoLarge = this.photoLarge,
            indications = this.indications.toIndicationModel(),
            interactions = this.interactions.toInteracionModel(),
            effects = this.effects.toEffectsModel()
        )
    }
}