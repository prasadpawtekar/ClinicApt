package com.aapolis.sqlitedemo1.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


// Job of DBHelper to create database and create tables in that database
class DBHelper(cntx: Context): SQLiteOpenHelper(cntx, Constants.DB_NAME, null, Constants.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constants.CREATE_PATIENT_TABLE)
        db?.execSQL(Constants.CREATE_DOCTOR_TABLE)
        db?.execSQL(Constants.CREATE_APPOINTMENT_TABLE)
        Log.d("DBHelper", "onCreate: patient, doctor and appointment tables are created.")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion == 1 && newVersion == 2) {
            db?.execSQL(Constants.CREATE_DOCTOR_TABLE)
            db?.execSQL(Constants.CREATE_APPOINTMENT_TABLE)
            Log.d("DBHelper", "onUpgrade: doctor and appointment tables are created.")
        }
    }
}
