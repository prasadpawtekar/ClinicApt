package com.aapolis.sqlitedemo1.sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.aapolis.sqlitedemo1.data.Appointment
import com.aapolis.sqlitedemo1.data.AppointmentDetails

class AppointmentDao(cntx: Context) {
    val db = DBHelper(cntx).writableDatabase

    fun addAppointment(apt: Appointment): Long {
        val values = ContentValues()
        values.put("doctorId", apt.doctorId)
        values.put("patientId", apt.patientId)
        values.put("aptDateTime", apt.aptDataTime)
        return db.insert("appointment", null, values)
    }

    @SuppressLint("Range")
    fun getAllAppointments(): List<AppointmentDetails> {
        val list = ArrayList<AppointmentDetails>()

        val query = """
            SELECT aptNo, patient.patientId, patient.name as pName, patient.mobileNo, 
            doctor.doctorId, doctor.name as dName, doctor.mobileNo as dMobileNo, aptDateTime
            FROM patient, doctor, appointment
            WHERE patient.patientId=appointment.patientId
            AND appointment.doctorId=doctor.doctorId
        """.trimIndent()

        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val aptNo = cursor.getLong(cursor.getColumnIndex("aptNo"))
            val pId = cursor.getLong(cursor.getColumnIndex("patientId"))
            val pName = cursor.getString(cursor.getColumnIndex("pName"))
            val mobileNo = cursor.getString(cursor.getColumnIndex("mobileNo"))
            val doctorId = cursor.getLong(cursor.getColumnIndex("doctorId"))
            val dName = cursor.getString(cursor.getColumnIndex("dName"))
            val dMobileNo = cursor.getString(cursor.getColumnIndex("dMobileNo"))
            val aptDateTime = cursor.getLong(cursor.getColumnIndex("aptDateTime"))
            val aptDetails = AppointmentDetails(aptNo, doctorId, dName, dMobileNo, pId, pName, mobileNo, aptDateTime)
            list.add(aptDetails)
        }
        return list
    }

}