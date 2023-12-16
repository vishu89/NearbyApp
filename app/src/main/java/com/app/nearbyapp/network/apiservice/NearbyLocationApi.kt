package com.app.nearbyapp.network.apiservice

import com.app.nearbyapp.ui.organisms.nearbylocation.NearbyLocationData
import retrofit2.http.GET
import retrofit2.http.Query

interface NearbyLocationsApi {
    @GET("/2/venues")
    suspend fun fetchNearbyLocations(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("range") range: String
    ): NearbyLocationData
}