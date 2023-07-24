package com.mvanalytic.search.data.datasources

import com.mvanalytic.search.data.models.LocalEffects
import com.mvanalytic.search.data.models.LocalIndication
import com.mvanalytic.search.data.models.LocalInteraction
import com.mvanalytic.search.data.models.LocalMed

object LocalMedsDataSource {

    private val meds = mutableListOf(
        LocalMed(
            id = 1,
            nameEng = "Aspirin",
            nameSpa = "Aspirina",
            brand = "Bayer",
            dose = "81 mg",
            descriptionEng = "Aspirin is used to reduce fever and relieve mild to moderate pain from conditions such as muscle aches, toothaches, common cold, and headaches. It may also be used to reduce pain and swelling in conditions such as arthritis. Aspirin is known as a salicylate and a nonsteroidal anti-inflammatory drug (NSAID).",
            descriptionSpa = "La aspirina se usa para reducir la fiebre y aliviar el dolor leve a moderado de afecciones como dolores musculares, dolores de muelas, resfriado común y dolores de cabeza. También se puede usar para reducir el dolor y la hinchazón en condiciones como la artritis. La aspirina es conocida como un salicilato y un fármaco antiinflamatorio no esteroideo (AINE).",
            routeEng = "Oral",
            routeSpa = "Oral",
            photoSmall = "https://www.meijer.com/content/dam/meijer/product/0031/28/4306/13/0031284306132_1_A1C1_1200.png",
            photoMedium = null,
            photoLarge = null,
            indications = LocalIndication(
                id = 1,
                descriptionEng = "Drink a full glass of water (8 ounces/240 milliliters) with it unless your doctor tells you otherwise.",
                descriptionSpa = "Consúmalo con un vaso lleno de agua (8 onzas/240 mililitros) al menos que su médico lo contraindique"
            ),
            interactions = LocalInteraction(
                id = 1,
                descriptionSpa = "Before using this product, consult your doctor if you have recently received certain live vaccines (such as varicella vaccine, live flu vaccine)",
                descriptionEng = "Antes de utilizar este producto, consulte a su doctor si ha sido vacunado previamente (varicela, gripe)"
            ),
            effects = LocalEffects(
                id = 1,
                descriptionEng = "Do not crush or chew enteric-coated tablets. Doing so can increase stomach upset.",
                descriptionSpa = "No mastique las tabletas. Hacerlo incrementa el estrés al estómago."
            )
        ),
        LocalMed(
            id = 2,
            nameEng = "Cataflam",
            nameSpa = "Cataflam",
            brand = "Novartis",
            dose = "50 mg",
            descriptionEng = "Also known as Diclofenac, it is used to relieve mild to moderate pain and swelling (inflammation) from various conditions such as headache, dental pain, menstrual cramps, and muscle aches. Diclofenac is known as a nonsteroidal anti-inflammatory drug (NSAID).",
            descriptionSpa = "También conocido como diclofenaco, se usa para aliviar el dolor y la hinchazón (inflamación) leves a moderados de diversas afecciones, como dolor de cabeza, dolor dental, calambres menstruales y dolores musculares. El diclofenaco es conocido como un fármaco antiinflamatorio no esteroideo (AINE).",
            routeEng = "Oral",
            routeSpa = "Oral",
            photoSmall = "https://farmaprime.com.ve/wp-content/uploads/2022/06/6301-FARMACIA-MARACAIBO.jpg",
            photoMedium = null,
            photoLarge = null,
            indications = LocalIndication(
                id = 2,
                descriptionEng = "Do not lie down for at least 10 minutes after you have taken this drug",
                descriptionSpa = "No se acueste por al menos 10 minutos después de que haya consumido este medicamento."
            ),
            interactions = LocalInteraction(
                id = 2,
                descriptionSpa = "Antes de utilizar este producto, consulte a su doctor si ha sido vacunado previamente (varicela, gripe)",
                descriptionEng = "Some products that may interact with this drug include: mifepristone, acetazolamide, blood thinners (such as warfarin, heparin) and corticosteroids (such as prednisone)."
            ),
            effects = LocalEffects(
                id = 2,
                descriptionEng = "If you are taking this medication for self-treatment of headache, get medical help right away if you also have trouble speaking, weakness on one side of the body, or sudden vision changes.",
                descriptionSpa = "Si se automedica para tratar dolores de cabeza, consiga ayuda médica inmediata si tiene dificultades para hablar, debilidad en un lado del cuerpo o pérdida de visión."
            )
        ),
        LocalMed(
            id = 3,
            nameEng = "Controlip",
            nameSpa = "Controlip",
            brand = "Abbot",
            dose = "135 mg",
            descriptionEng = "Also known as Diclofenac, it is used to relieve mild to moderate pain and swelling (inflammation) from various conditions such as headache, dental pain, menstrual cramps, and muscle aches. Diclofenac is known as a nonsteroidal anti-inflammatory drug (NSAID).",
            descriptionSpa = "También conocido como diclofenaco, se usa para aliviar el dolor y la hinchazón (inflamación) leves a moderados de diversas afecciones, como dolor de cabeza, dolor dental, calambres menstruales y dolores musculares. El diclofenaco es conocido como un fármaco antiinflamatorio no esteroideo (AINE).",
            routeEng = "Oral",
            routeSpa = "Oral",
            photoSmall = "https://res.cloudinary.com/prixz/image/upload/d_default_product_txh9zg.svg,w_500,q_auto/items/7501033959936.jpg",
            photoMedium = null,
            photoLarge = null,
            indications = LocalIndication(
                id = 3,
                descriptionEng = "Do not lie down for at least 10 minutes after you have taken this drug",
                descriptionSpa = "No se acueste por al menos 10 minutos después de que haya consumido este medicamento."
            ),
            interactions = LocalInteraction(
                id = 3,
                descriptionSpa = "Antes de utilizar este producto, consulte a su doctor si ha sido vacunado previamente (varicela, gripe)",
                descriptionEng = "Some products that may interact with this drug include: mifepristone, acetazolamide, blood thinners (such as warfarin, heparin) and corticosteroids (such as prednisone)."
            ),
            effects = LocalEffects(
                id = 3,
                descriptionEng = "If you are taking this medication for self-treatment of headache, get medical help right away if you also have trouble speaking, weakness on one side of the body, or sudden vision changes.",
                descriptionSpa = "Si se automedica para tratar dolores de cabeza, consiga ayuda médica inmediata si tiene dificultades para hablar, debilidad en un lado del cuerpo o pérdida de visión."
            )
        ),
        LocalMed(
            id = 4,
            nameEng = "Cataflam compuesto",
            nameSpa = "Abbot",
            brand = "Bayer",
            dose = "135 mg",
            descriptionEng = "Also known as Diclofenac, it is used to relieve mild to moderate pain and swelling (inflammation) from various conditions such as headache, dental pain, menstrual cramps, and muscle aches. Diclofenac is known as a nonsteroidal anti-inflammatory drug (NSAID).",
            descriptionSpa = "También conocido como diclofenaco, se usa para aliviar el dolor y la hinchazón (inflamación) leves a moderados de diversas afecciones, como dolor de cabeza, dolor dental, calambres menstruales y dolores musculares. El diclofenaco es conocido como un fármaco antiinflamatorio no esteroideo (AINE).",
            routeEng = "Oral",
            routeSpa = "Oral",
            photoSmall = "https://img.freepik.com/fotos-premium/jeringas-equipos-medicos-farmacia-productos-medicos-produccion-dispositivos-medicos_291852-80.jpg?w=2000",
            photoMedium = null,
            photoLarge = null,
            indications = LocalIndication(
                id = 3,
                descriptionEng = "Do not lie down for at least 10 minutes after you have taken this drug",
                descriptionSpa = "No se acueste por al menos 10 minutos después de que haya consumido este medicamento."
            ),
            interactions = LocalInteraction(
                id = 3,
                descriptionSpa = "Antes de utilizar este producto, consulte a su doctor si ha sido vacunado previamente (varicela, gripe)",
                descriptionEng = "Some products that may interact with this drug include: mifepristone, acetazolamide, blood thinners (such as warfarin, heparin) and corticosteroids (such as prednisone)."
            ),
            effects = LocalEffects(
                id = 3,
                descriptionEng = "If you are taking this medication for self-treatment of headache, get medical help right away if you also have trouble speaking, weakness on one side of the body, or sudden vision changes.",
                descriptionSpa = "Si se automedica para tratar dolores de cabeza, consiga ayuda médica inmediata si tiene dificultades para hablar, debilidad en un lado del cuerpo o pérdida de visión."
            )
        )
    )

    fun getMeds(): List<LocalMed> = meds
}