package ru.netology.wildberes.model

import ru.netology.wildberes.dto.AirTravel

data class FeedModel(
                     val airTravels: List<AirTravel> = emptyList(),
                     val loading: Boolean = false,
                     val error: Boolean = false,
                     val empty: Boolean = false,
                     val refreshing: Boolean = false,

)
