package com.mvanalytic.search.data.models


data class LocalMed (
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
    val indications: LocalIndication,
    val interactions: LocalInteraction,
    val effects: LocalEffects
    )