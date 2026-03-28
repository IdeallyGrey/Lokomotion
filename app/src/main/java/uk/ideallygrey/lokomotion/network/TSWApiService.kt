package uk.ideallygrey.lokomotion.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

//private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"
//private const val BASE_URL = "http://100.121.60.119:31270"
private const val BASE_URL = "http://100.121.60.119:31270"



private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(uk.ideallygrey.lokomotion.network.BASE_URL)
    .build()

interface TSWApiService {
//    @GET("photos")
//    suspend fun getPhotos(): List<MarsPhoto>
    @GET("get/CurrentDrivableActor.Function.HUD_GetSpeed")
    suspend fun getPhotos(@Header("DTGCommKey") token: String): TSWResponse
}

object TSWApi {
    val retrofitService: TSWApiService by lazy {
        uk.ideallygrey.lokomotion.network.retrofit.create(TSWApiService::class.java)
    }
}