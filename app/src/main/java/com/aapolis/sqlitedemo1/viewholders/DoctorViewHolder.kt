package com.aapolis.sqlitedemo1.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.sqlitedemo1.R
import com.aapolis.sqlitedemo1.data.Doctor

class DoctorViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val tvDoctorName = view.findViewById<TextView>(R.id.tv_doctor_name)

    fun setData(doctor: Doctor) {
        tvDoctorName.text = doctor.name
    }
}