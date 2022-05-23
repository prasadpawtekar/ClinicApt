package com.aapolis.sqlitedemo1.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.sqlitedemo1.R
import com.aapolis.sqlitedemo1.data.Doctor
import com.aapolis.sqlitedemo1.viewholders.DoctorViewHolder

class DoctorAdapter(val doctors: List<Doctor>): RecyclerView.Adapter<DoctorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.view_holder_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.setData(doctors[position])
        if(this::doctorSelectedListener.isInitialized) {
            holder.itemView.setOnClickListener {
                doctorSelectedListener(doctors[position], position)
            }
        }
    }

    override fun getItemCount() = doctors.size

    lateinit var doctorSelectedListener: (Doctor, Int) -> Unit

    fun setOnDoctorSelectedListener(listener: (Doctor, Int) -> Unit) {
        this.doctorSelectedListener = listener
    }
}