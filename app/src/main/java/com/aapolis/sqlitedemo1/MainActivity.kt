package com.aapolis.sqlitedemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.aapolis.sqlitedemo1.data.Patient
import com.aapolis.sqlitedemo1.sql.PatientDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var patientDao: PatientDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        patientDao = PatientDao(baseContext)
        btn_save.setOnClickListener {
            addPatient()
        }

        btn_show_all.setOnClickListener {
            startActivity(Intent(baseContext, PatientsListActivity::class.java))
        }

        btn_goto_add_doctor.setOnClickListener {
            startActivity(Intent(baseContext, AddDoctorActivity::class.java))
        }
        btn_new_apt.setOnClickListener {
            startActivity(Intent(baseContext, NewAppointmentActivity::class.java))
        }

        btn_goto_apts.setOnClickListener {
            startActivity(Intent(baseContext, AppointmentsActivity::class.java))
        }
    }

    fun showMessage(title:String, msg: String) {

        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton("Ok") {
                    d, which ->
                d.dismiss()
            }
            .create()

        dialog.show()
    }

    private fun addPatient() {
        val name = et_patient_name.text.toString()
        val gender = if(rb_male.isChecked) "M" else "F"
        val mobileNo = et_mobile_no.text.toString()
        val patient = Patient(0, name, gender, mobileNo)
        val patientId = patientDao.addPatient(patient)
        if(patientId > 0) {
            showMessage("Success!", "Patient added successfully with patient id: $patientId")
            rb_female.isChecked = false
            rb_male.isChecked = false
            et_patient_name.text.clear()
            et_mobile_no.text.clear()
            et_patient_name.requestFocus()

        } else {
            showMessage("Failure", "Failed to add patient to database.")
        }
    }
}

