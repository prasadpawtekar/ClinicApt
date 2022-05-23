package com.aapolis.sqlitedemo1.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.sqlitedemo1.R
import com.aapolis.sqlitedemo1.data.Patient

class PatientViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val tvName: TextView = view.findViewById(R.id.tv_name)
    val tvGender: TextView = view.findViewById(R.id.tv_gender)
    val tvMobileNo: TextView = view.findViewById(R.id.tv_mobile_no)

    val btnDelete: ImageView = view.findViewById(R.id.btn_delete)
    val btnEdit: ImageView = view.findViewById(R.id.btn_edit)

    fun setData(patient: Patient) {
        tvName.text = "Name : ${patient.name}"
        tvGender.text = "Gender : ${patient.gender}"
        tvMobileNo.text = "Contact : ${patient.mobileNo}"
    }

}