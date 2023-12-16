package com.app.nearbyapp.ui.screens.locationlisting

import com.app.nearbyapp.network.apiservice.NearbyLocationsApi
import com.app.nearbyapp.ui.organisms.nearbylocation.NearbyLocationData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationListingRepo {

    private val retrofitClient = Retrofit.Builder().baseUrl("https://api.seatgeek.com").addConverterFactory(GsonConverterFactory.create()).build()

    private val apiService = retrofitClient.create(NearbyLocationsApi::class.java)

    suspend fun fetchNearbyLocations(itemsPerPage: Int, lat: Double, lon: Double, range: Int, page: Int) : NearbyLocationData {
        val clientId = "Mzg0OTc0Njl8MTcwMDgxMTg5NC44MDk2NjY5"
        return apiService.fetchNearbyLocations(clientId, page, itemsPerPage, lat, lon, "${range}mi")
    }

}