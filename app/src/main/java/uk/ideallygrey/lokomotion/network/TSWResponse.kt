package uk.ideallygrey.lokomotion.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
// SPEED
@Serializable
data class TSWResponse(
    @SerialName(value = "Result")
    val result: String,
    @SerialName(value = "Values")
    val values: SpeedValue
)

@Serializable
data class SpeedValue(
    @SerialName(value = "Speed (ms)")
    val speed: Float
)
// ALL PATCHES
@Serializable
data class TSWResponsePatch(
    @SerialName(value = "Result")
    val result: String
)

// GET PLAYER INFO
@Serializable
data class TSWResponseDriverAidPlayerInfo(
    @SerialName(value = "Result")
    val result: String,
    @SerialName(value = "Values")
    val values: DriverAidPlayerInfoValues
)

@Serializable
data class DriverAidPlayerInfoValues(
    val geoLocation: GeoLocation,
    val currentTile: CurrentTile,
    val playerProfileName: String,
    val cameraMode: String,
    val currentServiceName: String
)

@Serializable
data class GeoLocation(
    val longitude: Float,
    val latitude: Float
)

@Serializable
data class CurrentTile(
    val x: Int,
    val y: Int
)