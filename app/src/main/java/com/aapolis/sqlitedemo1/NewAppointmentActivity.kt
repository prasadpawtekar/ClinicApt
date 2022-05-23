package com.aapolis.sqlitedemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.aapolis.sqlitedemo1.data.Appointment
import com.aapolis.sqlitedemo1.data.AppointmentDetails
import com.aapolis.sqlitedemo1.data.Doctor
import com.aapolis.sqlitedemo1.data.Patient
import com.aapolis.sqlitedemo1.sql.AppointmentDao
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_new_appointment.*
import java.util.*

class NewAppointmentActivity : AppCompatActivity() {
    lateinit var selectedPatient: Patient
    lateinit var selectedDoctor: Doctor
    lateinit var aptDao: AppointmentDao
    var selectedYear = -1
    var selectedMonth = -1
    var selectedDay = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_appointment)

        aptDao = AppointmentDao(baseContext)
        btn_select_patient.setOnClickListener {
            val spIntent = Intent(baseContext, PatientsListActivity::class.java)
            startActivityForResult(spIntent, RC_PATIENT)
        }

        btn_select_doctor.setOnClickListener {
            val sdIntent = Intent(baseContext, DoctorsActivity::class.java)
            startActivityForResult(sdIntent, RC_DOCTOR)
        }

        btn_create_apt.setOnClickListener {
            createAppointment()
        }

        cv_calendar.setOnDateChangeListener { calendarView, year, month, day ->
            selectedYear = year
            selectedMonth = month
            selectedDay = day
            Log.d("SelectedDate", "onCreate: y = $year, m = $month, d = $day")
        }
    }

    private fun createAppointment() {

        if(!this::selectedDoctor.isInitialized) {
            Snackbar.make(parent_layout, "Please select doctor", Snackbar.LENGTH_LONG).show()
            return
        }

        if(!this::selectedPatient.isInitialized) {
            Snackbar.make(parent_layout, "Please select patient", Snackbar.LENGTH_LONG).show()
            return
        }



        val hour = tp_time.hour
        val minutes = tp_time.minute

        val calendar = Calendar.getInstance()

        calendar.set(Calendar.YEAR, selectedYear)
        calendar.set(Calendar.MONTH, selectedMonth)
        calendar.set(Calendar.DAY_OF_MONTH, selectedDay)


        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minutes)

        val aptDateTime = calendar.time.time

        Log.d("SelectedDate", "createAppointment: $aptDateTime")
        val appointment = Appointment(0, selectedDoctor.doctorId, selectedPatient.patientId, aptDateTime)
        val aptNo = aptDao.addAppointment(appointment)

        if(aptNo>0) {
            val dialog = AlertDialog.Builder(this).apply {
                setTitle("Success!")
                setMessage("Appointment created with Appointment No. : $aptNo")
                create()
            }
            dialog.show()
        } else {
            Toast.makeText(baseContext, "Failed to create appointment. Please retry.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            if(requestCode == RC_DOCTOR) {
                val doctor = data?.extras?.getParcelable<Doctor>("doctor")
                doctor?.let {
                    selectedDoctor = it
                    tv_selected_doctor.text = it.name
                }
            } else if(requestCode == RC_PATIENT) {
                val patient = data?.extras?.getParcelable<Patient>("patient")
                patient?.let{
                    selectedPatient = it
                    tv_selected_patient.text = it.name
                }
            }
        }
    }
    companion object {
        const val RC_PATIENT = 10
        const val RC_DOCTOR = 20
    }
}