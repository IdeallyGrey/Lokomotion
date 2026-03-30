package uk.ideallygrey.lokomotion.ui.screens

import uk.ideallygrey.lokomotion.network.GeoLocation
import uk.ideallygrey.lokomotion.network.TSWApiService

data class SimData(
    var speed: Float?,
    var geoLocation: GeoLocation?,
    var playerServiceName: String?
)