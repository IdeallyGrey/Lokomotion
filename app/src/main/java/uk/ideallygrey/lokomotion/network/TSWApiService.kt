package uk.ideallygrey.lokomotion.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH

//private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"
//private const val BASE_URL = "http://100.121.60.119:31270"
private const val BASE_URL = "http://100.121.60.119:31270"



private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(uk.ideallygrey.lokomotion.network.BASE_URL)
    .build()

interface TSWApiService {
    @GET("get/CurrentDrivableActor.Function.HUD_GetSpeed")
    suspend fun getSpeed(@Header("DTGCommKey") token: String): TSWResponse

    @GET("get/DriverAid.PlayerInfo")
    suspend fun getDriverAidPlayerInfo(@Header("DTGCommKey") token: String): TSWResponseDriverAidPlayerInfo

    @PATCH("set/VirtualRailDriver.Enabled?Value=1")
    suspend fun enableRailDriver(@Header("DTGCommKey") token: String): TSWResponsePatch

    @PATCH("set/VirtualRailDriver.Horn Forward?Value=0")
    suspend fun setHornOff(@Header("DTGCommKey") token: String): TSWResponsePatch

    @PATCH("set/VirtualRailDriver.Horn Forward?Value=1")
    suspend fun setHornOn(@Header("DTGCommKey") token: String): TSWResponsePatch
}

object TSWApi {
    val retrofitService: TSWApiService by lazy {
        uk.ideallygrey.lokomotion.network.retrofit.create(TSWApiService::class.java)
    }
}