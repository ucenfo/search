package com.mvanalytic.search.domian.models

data class MedModel(
    val id: Long,
    val nameEng: String,
    val nameSpa: String,
    val brand: String,
    val dose: String,
    val descriptionEng: String,
    val descriptionSpa: String,
    val routeEng: String,
    val routeSpa: String,
    val photoSmall: String,
    val photoMedium: String?,
    val photoLarge: String?,
    val indications: IndicationModel,
    val interactions: InteractionModel,
    val effects: EffectsModel
)
