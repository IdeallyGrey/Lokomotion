package uk.ideallygrey.lokomotion.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(uk.ideallygrey.lokomotion.network.BASE_URL)
    .build()

interface TSWApiService {
    @GET("photos")
    suspend fun getPhotos(): String
}

object TSWApi {
    val retrofitService: TSWApiService by lazy {
        uk.ideallygrey.lokomotion.network.retrofit.create(TSWApiService::class.java)
    }
}