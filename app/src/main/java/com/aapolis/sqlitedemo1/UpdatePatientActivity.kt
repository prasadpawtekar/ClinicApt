package com.aapolis.sqlitedemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aapolis.sqlitedemo1.data.Patient
import com.aapolis.sqlitedemo1.sql.PatientDao
import kotlinx.android.synthetic.main.activity_update_patient.*

class UpdatePatientActivity : AppCompatActivity() {
    lateinit var selectedPatient: Patient
    lateinit var patientDao: PatientDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_patient)

        patientDao = PatientDao(baseContext)
        intent.extras?.getParcelable<Patient>("patient")?.let {
            selectedPatient = it
            et_patient_name.setText(selectedPatient.name)

            if(selectedPatient.gender == "M")
                rb_male.isChecked = true
            else
                rb_female.isChecked = true

            et_mobile_no.setText(selectedPatient.mobileNo)
        }

        btn_update.setOnClickListener {
            val gender = if(rb_male.isChecked) "M" else "F"
            val patient = Patient(selectedPatient.patientId,
                et_patient_name.text.toString(),
                gender,
                et_mobile_no.text.toString()
            )

            val updated = patientDao.updatePatient(patient)
            if(updated) {
                Toast.makeText(baseContext, "Record updated successfully.", Toast.LENGTH_LONG).show()
                val data = Intent()
                data.putExtra("updatedPatient", patient)
                setResult(RESULT_OK, data)
                finish()
            } else {
                Toast.makeText(baseContext, "Failed to update patient details. Please retry.", Toast.LENGTH_LONG).show()
            }
        }
    }
}