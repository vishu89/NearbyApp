package com.app.nearbyapp.ui.screens.locationlisting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.nearbyapp.base.BaseViewModel
import com.app.nearbyapp.base.organisms.BaseOrganismData
import com.app.nearbyapp.models.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationListingVM(private val repo: LocationListingRepo) : BaseViewModel() {

    val organismsLd: LiveData<List<BaseOrganismData>>
        get() = _organismsLdLd
    private val _organismsLdLd = MutableLiveData<List<BaseOrganismData>>()

    val showLoaderLd = MutableLiveData<Boolean>()


    fun fetchNearbyLocations(location: Location, range: Int = 12, page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO + defaultExceptionHandler) {
            repo.fetchNearbyLocations(
                10,
                location.latitude,
                location.longitude,
                range,
                page
            ).venues?.let {
                _organismsLdLd.postValue(it)
            }
            showLoaderLd.postValue(false)
        }
    }
}