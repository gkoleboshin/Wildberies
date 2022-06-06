package ru.netology.wildberes.reposytory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.wildberes.dto.AirTravel


interface AirTravelRepository {
    fun getAll(): List<AirTravel>
}