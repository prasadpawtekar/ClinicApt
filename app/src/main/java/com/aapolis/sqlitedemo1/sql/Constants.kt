package com.aapolis.sqlitedemo1.sql

object Constants {
    const val DB_VERSION = 2
    const val DB_NAME = "ClinicDB"
    val CREATE_PATIENT_TABLE = """
            CREATE TABLE patient
            (
            	patientId INTEGER PRIMARY KEY AUTOINCREMENT,
            	name TEXT,
            	gender CHAR,
            	mobileNo TEXT
            )
        """.trimIndent()

    val CREATE_DOCTOR_TABLE = """
        CREATE TABLE doctor (
            doctorId INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT,
            mobileNo TEXT
        )
    """.trimIndent()

    val CREATE_APPOINTMENT_TABLE = """
        CREATE TABLE appointment(
        aptNo INTEGER PRIMARY KEY AUTOINCREMENT,
        doctorId INTEGER REFERENCES doctor(doctorId),
        patientId INTEGER REFERENCES patient(patientId),
        aptDateTime INTEGER)
    """.trimIndent()
}