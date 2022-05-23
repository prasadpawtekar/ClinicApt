package com.aapolis.sqlitedemo1.viewholders

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.sqlitedemo1.R
import com.aapolis.sqlitedemo1.data.AppointmentDetails
import java.text.SimpleDateFormat
import java.util.*

class AppointmentViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val tvAptNo = view.findViewById<TextView>(R.id.tv_apt_no)
    val tvPatientName = view.findViewById<TextView>(R.id.tv_patient_name)
    val tvMobileNo = view.findViewById<TextView>(R.id.tv_pmobile_no)
    val tvDoctorName = view.findViewById<TextView>(R.id.tv_doctor_name)
    val tvAptDateTime = view.findViewById<TextView>(R.id.tv_apt_date_time)

    fun setData(apt: AppointmentDetails) {
        tvAptNo.text = "Appointment No. : ${apt.aptNo}"
        tvPatientName.text = "Patient Name : ${apt.patientName}"
        tvMobileNo.text = "Patient Mobile No. : ${apt.pMobileNo}"
        tvDoctorName.text = "Doctor Name : ${apt.doctorName}"

        val simpleDateFormate = SimpleDateFormat("dd MMM, yyyy hh:mm a")
        val formattedDateTime = simpleDateFormate.format(Date(apt.aptDataTime))
        tvAptDateTime.text = formattedDateTime
    }
}