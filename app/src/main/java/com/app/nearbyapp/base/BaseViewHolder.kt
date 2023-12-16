package com.app.nearbyapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.nearbyapp.base.organisms.BaseOrganismData

abstract class BaseViewHolder(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(data: BaseOrganismData)
}