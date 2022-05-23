package com.aapolis.sqlitedemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aapolis.sqlitedemo1.R
import com.aapolis.sqlitedemo1.data.Doctor
import com.aapolis.sqlitedemo1.sql.DoctorDao
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_doctor.*

class AddDoctorActivity : AppCompatActivity() {
    lateinit var doctorDao: DoctorDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_doctor)

        doctorDao = DoctorDao(baseContext)
        btn_add_doctor.setOnClickListener {
            addDoctor()
        }
        Log.d("AllDoctors", "onCreate: ${doctorDao.getDoctors()}")
    }

    private fun addDoctor() {

        val name = et_doctor_name.text.toString()
        val mobileNo = et_doctor_mobile_no.text.toString()
        val doctor = Doctor(0, name, mobileNo)
        val doctorId = doctorDao.addDoctor(doctor)

        if(doctorId>0) {
            val snackBar = Snackbar.make(layout_add_doctor, "Doctor added successfully with Doctor Id: $doctorId", Snackbar.LENGTH_LONG)
            snackBar.setAction("Ok") {
                snackBar.dismiss()
            }
            snackBar.show()
        } else {
            val snackBar = Snackbar.make(layout_add_doctor, "Failed add doctor. Please retry.", Snackbar.LENGTH_LONG)
            snackBar.setAction("Ok") {
                snackBar.dismiss()
            }
            snackBar.show()
        }
    }
}