package com.app.nearbyapp.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel: ViewModel() {
    val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("LocationListingVM", throwable.message.toString())
    }
}