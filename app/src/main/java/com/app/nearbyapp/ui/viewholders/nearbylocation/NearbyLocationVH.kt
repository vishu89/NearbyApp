package com.app.nearbyapp.ui.viewholders.nearbylocation

import android.annotation.SuppressLint
import com.app.nearbyapp.base.BaseInteraction
import com.app.nearbyapp.base.BaseViewHolder
import com.app.nearbyapp.base.organisms.BaseOrganismData
import com.app.nearbyapp.databinding.LayoutNearbyLocationsBinding

class NearbyLocationVH(
    private val binding: LayoutNearbyLocationsBinding,
    interaction: BaseInteraction
) :
    BaseViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            currentData?.url?.let {
                interaction.onNearbyLocationClicked(it)
            }
        }
    }

    private var currentData: NearbyLocationOrganismData? = null

    @SuppressLint("SetTextI18n")
    override fun bind(data: BaseOrganismData) {
        currentData = data as? NearbyLocationOrganismData ?: return
        binding.title.text = currentData?.name
        binding.subtitle.text = "${currentData?.address},${currentData?.city}"
    }

}