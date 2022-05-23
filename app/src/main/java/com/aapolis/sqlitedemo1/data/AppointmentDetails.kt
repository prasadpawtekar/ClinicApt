package com.aapolis.sqlitedemo1.data

data class AppointmentDetails(
    val aptNo: Long,
    val doctorId: Long,
    val doctorName: String,
    val dMobileNo: String,
    val patientId: Long,
    val patientName: String,
    val pMobileNo: String,
    val aptDataTime: Long,
)
