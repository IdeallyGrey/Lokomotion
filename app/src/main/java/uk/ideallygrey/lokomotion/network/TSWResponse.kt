package uk.ideallygrey.lokomotion.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TSWResponse(
    @SerialName(value = "Result")
    val result: String,
    @SerialName(value = "Values")
    val values: Value
)

@Serializable
data class Value(
    @SerialName(value = "Speed (ms)")
    val speed: Float
)