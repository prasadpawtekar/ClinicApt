package com.aapolis.sqlitedemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.aapolis.sqlitedemo1.adapters.PatientAdapter
import com.aapolis.sqlitedemo1.data.Patient
import com.aapolis.sqlitedemo1.sql.PatientDao
import kotlinx.android.synthetic.main.activity_patients_list.*

class PatientsListActivity : AppCompatActivity() {
    lateinit var patients: ArrayList<Patient>
    lateinit var adapter: PatientAdapter
    lateinit var patientDao: PatientDao
    var selectedPatientIndex = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patients_list)

        rv_patients.layoutManager = LinearLayoutManager(baseContext)
        patientDao = PatientDao(baseContext)
        patients = patientDao.getAllPatients()
        adapter = PatientAdapter(patients)

        rv_patients.adapter = adapter

        adapter.setOnDeleteClickListener { patient, position ->
            deletePatient(patient, position)
        }

        adapter.setOnEditClickListener { patient, position ->
            selectedPatientIndex = position
            val updatePatientIntent = Intent(baseContext, UpdatePatientActivity::class.java)
            updatePatientIntent.putExtra("patient", patient)
            startActivityForResult(updatePatientIntent, RC_EDIT)
        }

        adapter.setOnPatientSelectedListener { patient, position ->
            val data = Intent()
            data.putExtra("patient", patient)
            setResult(RESULT_OK, data)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            if(requestCode == RC_EDIT) {
                data?.extras?.getParcelable<Patient>("updatedPatient")?.let {
                    patients.set(selectedPatientIndex, it)
                    adapter.notifyItemChanged(selectedPatientIndex)
                }
            }
        }
    }
    val RC_EDIT = 100
    private fun deletePatient(patient: Patient, position: Int) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Confirm Action")
            .setMessage("Are you sure you want delete this patient data?")
            .setPositiveButton("Yes") {
                d, which ->

                val deleted = patientDao.deletePatient(patient.patientId)
                if(deleted) {
                    patients.removeAt(position)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(baseContext, "Patient deleted successfully.", Toast.LENGTH_LONG).show()
                    d.dismiss()
                } else {
                    Toast.makeText(baseContext, "Failed to delete patient data. Please retry.", Toast.LENGTH_LONG).show()
                    d.dismiss()
                }
            }
            .setNegativeButton("No") {
                d, which ->
                d.dismiss()
            }
            .create()

        dialog.show()
    }
}