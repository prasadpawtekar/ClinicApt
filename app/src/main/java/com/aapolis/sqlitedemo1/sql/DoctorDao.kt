package com.aapolis.sqlitedemo1.sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.aapolis.sqlitedemo1.data.Doctor

class DoctorDao(cntx: Context) {
    val dbHelper = DBHelper(cntx)
    val db = dbHelper.writableDatabase

    fun addDoctor(doctor: Doctor): Long {
        val values = ContentValues().apply {
            put("name", doctor.name)
            put("mobileNo", doctor.mobileNo)
        }
        return db.insert("doctor", null, values)

    }

    @SuppressLint("Range")
    fun getDoctors(): List<Doctor> {
        val list = ArrayList<Doctor>()
        val cursor = db.query("doctor", null, null, null, null, null, "name" )
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("doctorId"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val mobileNo = cursor.getString(cursor.getColumnIndex("mobileNo"))
            val doctor = Doctor(id, name, mobileNo)
            list.add(doctor)
        }

        return list
    }
}