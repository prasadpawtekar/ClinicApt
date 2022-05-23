package com.aapolis.sqlitedemo1.data

data class Appointment(
    val aptNo: Long,
    val doctorId: Long,
    val patientId: Long,
    val aptDataTime: Long
)
