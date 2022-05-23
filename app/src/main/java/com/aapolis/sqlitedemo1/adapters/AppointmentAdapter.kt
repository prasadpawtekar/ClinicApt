package com.aapolis.sqlitedemo1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.sqlitedemo1.R
import com.aapolis.sqlitedemo1.data.AppointmentDetails
import com.aapolis.sqlitedemo1.viewholders.AppointmentViewHolder

class AppointmentAdapter(val apts: List<AppointmentDetails>): RecyclerView.Adapter<AppointmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.view_holder_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.setData(apts[position])
    }

    override fun getItemCount() = apts.size
}