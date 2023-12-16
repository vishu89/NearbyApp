package com.app.nearbyapp.ui.screens.locationlisting

import com.app.nearbyapp.KeyUtils
import com.app.nearbyapp.network.apiservices.NearbyLocationsApi
import com.app.nearbyapp.network.retrofit.RetrofitUtils
import com.app.nearbyapp.ui.organisms.nearbylocation.NearbyLocationData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationListingRepo {

    private val apiService = RetrofitUtils.client.create(NearbyLocationsApi::class.java)

    suspend fun fetchNearbyLocations(
        itemsPerPage: Int,
        lat: Double,
        lon: Double,
        range: Int,
        page: Int
    ): NearbyLocationData {
        return apiService.fetchNearbyLocations(
            KeyUtils.clientId,
            page,
            itemsPerPage,
            lat,
            lon,
            "${range}mi"
        )
    }

}