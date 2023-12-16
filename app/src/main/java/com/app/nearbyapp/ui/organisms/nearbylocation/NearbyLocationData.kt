package com.app.nearbyapp.ui.organisms.nearbylocation

import com.app.nearbyapp.ui.viewholders.nearbylocation.NearbyLocationOrganismData
import com.google.gson.annotations.SerializedName

data class NearbyLocationData(
    @SerializedName("venues") val venues: List<NearbyLocationOrganismData>? = null
)
