package ru.netology.wildberes.dto



data class AirTravel (
            val startCity:String,
            val startCityCode:String,
            val endCity:String,
            val endCityCode: String,
            val startDate:String,
            val endDate:String,
            val price:Long,
            val searchToken:String,
            var likes:Long = 0L,
            var likeByMe:Boolean = false
        )