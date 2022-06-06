package ru.netology.wildberes.adapter

import ru.netology.wildberes.dto.AirTravel

interface AirTravelInteractionListener {
    fun onLike(airTravel: AirTravel)
    fun onViewAirTravel(airTravel:AirTravel)
    fun onGetAll()
}