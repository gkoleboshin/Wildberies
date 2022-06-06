package ru.netology.wildberes.reposytory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import ru.netology.wildberes.dto.AirTravel
import ru.netology.wildberes.dto.CheapModel
import ru.netology.wildberes.util.SingleLiveEvent
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class AirTravelRepositoryImpl : AirTravelRepository {


    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
    private val gson = Gson()
    private val typeToken = object : TypeToken<CheapModel>() {}

    companion object {
        private const val BASE_URL = "https://travel.wildberries.ru/statistics/v1/cheap"
        private val jsonType = "application/json".toMediaType()
    }


    override fun getAll(): List<AirTravel> {
        val request: Request = Request.Builder()
            .url("${BASE_URL}")
            .build()

        return client.newCall(request)
            .execute()
            .use {
                it.body?.string() ?: throw RuntimeException("body is null") }
            .let {
                gson.fromJson(it, typeToken.type)
            }
    }


}