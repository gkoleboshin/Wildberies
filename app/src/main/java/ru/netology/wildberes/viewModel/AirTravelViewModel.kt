package ru.netology.wildberes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.wildberes.adapter.AirTravelInteractionListener
import ru.netology.wildberes.dto.AirTravel
import ru.netology.wildberes.model.FeedModel
import ru.netology.wildberes.reposytory.AirTravelRepository
import ru.netology.wildberes.reposytory.AirTravelRepositoryImpl
import ru.netology.wildberes.util.SingleLiveEvent
import java.io.IOException
import kotlin.concurrent.thread

class AirTravelViewModel : ViewModel(), AirTravelInteractionListener {
    private val repository: AirTravelRepository = AirTravelRepositoryImpl()
    val navigeteToThisAirTravelScreen = SingleLiveEvent<String>()

    private val _data = MutableLiveData(FeedModel())
    val data: LiveData<FeedModel>
        get() = _data

    init {
        loadAirTravels()
    }

    fun loadAirTravels() {
        thread {
            _data.postValue(FeedModel(loading = true))
            try {
                val airTravels = repository.getAll()
                FeedModel(airTravels = airTravels, empty = airTravels.isEmpty())
            } catch (e: IOException) {
                FeedModel(error = true)
            }.also(_data::postValue)
        }
    }

    override fun onLike(airTravel: AirTravel) {


        val airTravels = data.value?.airTravels!!.map {
            if (it.searchToken != airTravel.searchToken) {
                it
            } else {
                it.likeByMe = !it.likeByMe
                if (!it.likeByMe) {
                    it.likes++
                } else {
                    it.likes--
                }
            }
        }
        FeedModel(
            airTravels = airTravels as List<AirTravel>,
            empty = airTravels.isEmpty()
        ).also { _data::postValue }
    }

    override fun onViewAirTravel(airTravel: AirTravel) {
        navigeteToThisAirTravelScreen.value = airTravel.searchToken
    }

    override fun onGetAll() {
        thread { repository.getAll() }
    }
}
