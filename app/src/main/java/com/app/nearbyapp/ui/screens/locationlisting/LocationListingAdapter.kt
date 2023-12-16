package com.app.nearbyapp.ui.screens.locationlisting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.nearbyapp.base.BaseInteraction
import com.app.nearbyapp.base.organisms.BaseOrganismData
import com.app.nearbyapp.databinding.LayoutNearbyLocationsBinding
import com.app.nearbyapp.ui.OrganismTypes
import com.app.nearbyapp.ui.viewholders.dummy.DummyVH
import com.app.nearbyapp.ui.viewholders.nearbylocation.NearbyLocationVH

class LocationListingAdapter(
    private val items: MutableList<BaseOrganismData>,
    private val interaction: BaseInteraction
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            OrganismTypes.NEARBY_LOCATION -> NearbyLocationVH(
                LayoutNearbyLocationsBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    )
                ), interaction
            )

            else -> DummyVH(View(parent.context))
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NearbyLocationVH -> {
                holder.bind(items[position])
            }
        }
    }

    fun setItems(models: List<BaseOrganismData>) {
        items.clear()
        items.addAll(models)
        notifyDataSetChanged()
    }
}