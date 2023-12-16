package com.app.nearbyapp.ui.screens.locationlisting

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.SeekBar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.nearbyapp.base.BaseInteraction
import com.app.nearbyapp.base.views.ViewBindingActivity
import com.app.nearbyapp.databinding.ActivityLocationListingBinding
import com.app.nearbyapp.location.LocationUtils
import com.app.nearbyapp.location.LocationUtils.PERMISSION_REQUEST_CODE
import com.app.nearbyapp.models.Location


/*
Local caching couldn't be implemented due to time constraints
 */
class LocationListingActivity : ViewBindingActivity<ActivityLocationListingBinding>(),
    ActivityCompat.OnRequestPermissionsResultCallback {

    // move interactions to new class
    private val itemAdapter by lazy {
        LocationListingAdapter(mutableListOf(), object : BaseInteraction {
            override fun onNearbyLocationClicked(url: String) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        })
    }

    private val viewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(LocationListingVM::class.java)) {
                    return LocationListingVM(LocationListingRepo()) as T
                } else {
                    throw Exception("Unknown class name: ${modelClass.simpleName}")
                }
            }
        })[LocationListingVM::class.java]
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            LocationUtils.fetchLocation(this) { latitude, longitude ->
                viewModel.fetchNearbyLocations(
                    Location(
                        latitude,
                        longitude
                    )
                )
            }
        }
    }

    override fun setupViews() {
        fetchLocation()
        setupObservers()
        setupRecyclerView()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.mySeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                LocationUtils.currentLocation.takeIf { it != null && p2 }?.let {
                    viewModel.fetchNearbyLocations(it, p1)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        binding.rvView.setOnScrollChangeListener { p0, p1, p2, p3, p4 ->
            if (p2 > 0 && (binding.rvView.adapter?.itemCount
                    ?: Int.MAX_VALUE) - getVisibleItemCount() <= 3
            ) {
                // Make pagination call here
            }
        }
    }

    // Time constraints, couldn't complete this
    private fun getVisibleItemCount(): Int {
        return 0
    }

    private fun fetchLocation() {
        LocationUtils.fetchLocation(this) { latitude, longitude ->
            viewModel.fetchNearbyLocations(
                Location(
                    latitude,
                    longitude
                )
            )
        }
    }

    private fun setupObservers() {
        viewModel.organismsLd.observe(this) {
            it ?: return@observe
            itemAdapter.setItems(it)
            binding.mySeekbar.visibility = View.VISIBLE
        }

        viewModel.showLoaderLd.observe(this) {
            it ?: return@observe
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun setupRecyclerView() {
        binding.rvView.apply {
            layoutManager = LinearLayoutManager(this@LocationListingActivity)
            adapter = itemAdapter
            addItemDecoration(
                object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        super.getItemOffsets(outRect, view, parent, state)
                        outRect.left = 24
                        outRect.top = 24
                        outRect.bottom = 24
                        outRect.right = 24
                    }
                }
            )
        }
    }

    override fun inflateBinding(layoutInflater: LayoutInflater): ActivityLocationListingBinding {
        return ActivityLocationListingBinding.inflate(layoutInflater)
    }
}