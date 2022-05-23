package com.aapolis.sqlitedemo1.sql

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.aapolis.sqlitedemo1.data.Patient

class PatientDao(context: Context) {
    val dbHelper = DBHelper(context)
    val db: SQLiteDatabase = dbHelper.writableDatabase


    fun addPatient(patient: Patient): Long {
        val values: ContentValues = ContentValues()

        values.put("name", patient.name)
        values.put("gender", patient.gender.toString())
        values.put("mobileNo", patient.mobileNo)

        val patientId: Long = db.insert("patient", null, values)
        return patientId
        /*
        No need to execute query mentioned below. db.insert() function internally generates that query and executes it on db.
        val query = "INSERT INTO patient(name, gender, mobileNo) values ('${patient.name}', '${patient.gender}', '${patient.mobileNo}')"
        db.execSQL(query)*/
    }

    fun getAllPatients(): ArrayList<Patient> {
        val patientsList = ArrayList<Patient>()

        val cursor: Cursor = db.query("patient", null, null, null, null, null, null)

        while(cursor.moveToNext()) {
            val patientId = cursor.getLong(0)
            val name = cursor.getString(1)
            val gender = cursor.getString(2)
            val mobileNo = cursor.getString(3)
            val patient = Patient(patientId, name, gender, mobileNo)
            patientsList.add(patient)
        }

        return patientsList
    }

    fun deletePatient(patientId: Long): Boolean {
        val numberOfRowsDeleted:Int = db.delete("patient", "patientId=$patientId", null)
        return numberOfRowsDeleted == 1
    }

    fun updatePatient(patient: Patient): Boolean {
        val values: ContentValues = ContentValues()

        values.put("name", patient.name)
        values.put("gender", patient.gender.toString())
        values.put("mobileNo", patient.mobileNo)

        val numberOfUpdatedRows = db.update("patient", values, "patientId=${patient.patientId}", null)

        return numberOfUpdatedRows == 1
    }
}