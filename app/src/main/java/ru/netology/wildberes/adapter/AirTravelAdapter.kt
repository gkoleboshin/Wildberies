package ru.netology.wildberes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.netology.wildberes.databinding.CardAirTravelBinding
import ru.netology.wildberes.dto.AirTravel

class AirTravelAdapter(
    private val interactionLisitner: AirTravelInteractionListener
) :ListAdapter<AirTravel,AirTravelViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirTravelViewHolder {
        val binding = CardAirTravelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AirTravelViewHolder(binding, interactionLisitner)
    }

    override fun onBindViewHolder(holder: AirTravelViewHolder, position: Int) {
        val airTravel = getItem(position)
        holder.bind(airTravel)
    }
    private object DiffCallback : DiffUtil.ItemCallback<AirTravel>() {
        override fun areItemsTheSame(oldItem: AirTravel, newItem: AirTravel): Boolean {
            return oldItem.searchToken == newItem.searchToken        }

        override fun areContentsTheSame(oldItem: AirTravel, newItem: AirTravel): Boolean {
            return oldItem == newItem
        }

    }
}