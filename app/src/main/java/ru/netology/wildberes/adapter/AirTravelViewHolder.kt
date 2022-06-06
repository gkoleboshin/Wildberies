package ru.netology.wildberes.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.netology.wildberes.databinding.CardAirTravelBinding
import ru.netology.wildberes.dto.AirTravel

class AirTravelViewHolder(
    private val binding: CardAirTravelBinding,
    private val interactionLisitner: AirTravelInteractionListener
): RecyclerView.ViewHolder(binding.root) {

    private lateinit var airTravel: AirTravel

    init {
        with(binding) {
            like.setOnClickListener {
                interactionLisitner.onLike(airTravel)
            }
            avatar.setOnClickListener{
                interactionLisitner.onViewAirTravel(airTravel)
            }
            departure.setOnClickListener {
                interactionLisitner.onViewAirTravel(airTravel)
            }
            departureCity.setOnClickListener {
                interactionLisitner.onViewAirTravel(airTravel)
            }
            arivial.setOnClickListener {
                interactionLisitner.onViewAirTravel(airTravel)
            }
            arivialCity.setOnClickListener {
                interactionLisitner.onViewAirTravel(airTravel)
            }
            departureD.setOnClickListener {
                interactionLisitner.onViewAirTravel(airTravel)
            }
            departureDate.setOnClickListener {
                interactionLisitner.onViewAirTravel(airTravel)
            }
            arivialD.setOnClickListener {
                interactionLisitner.onViewAirTravel(airTravel)
            }
            arivialDate.setOnClickListener {
                interactionLisitner.onViewAirTravel(airTravel)
            }
            text.setOnClickListener {
                interactionLisitner.onViewAirTravel(airTravel)
            }
            price.setOnClickListener {
                interactionLisitner.onViewAirTravel(airTravel)
            }

        }

    }

    fun bind(airTravel: AirTravel) {
        this.airTravel = airTravel
        with(binding) {
            arivialCity.text = airTravel.startCity
            departureCity.text = airTravel.endCity
            arivialDate.text = airTravel.startDate.toString()
            departureDate.text = airTravel.endDate.toString()
            price.text = airTravel.price.toString()
            like.text = airTravel.likes.toString()
            like.isChecked = airTravel.likeByMe

        }
    }
}