package com.app.nearbyapp.ui.viewholders.nearbylocation

import com.app.nearbyapp.base.organisms.BaseOrganismData
import com.app.nearbyapp.ui.OrganismTypes
import com.google.gson.annotations.SerializedName

data class NearbyLocationOrganismData(
    @SerializedName("name") val name: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("address") val address: String? = null,
    @SerializedName("url") val url: String? = null,
) : BaseOrganismData {
    override val type: Int
        get() = OrganismTypes.NEARBY_LOCATION
}