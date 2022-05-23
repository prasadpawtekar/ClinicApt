package com.aapolis.sqlitedemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aapolis.sqlitedemo1.adapters.AppointmentAdapter
import com.aapolis.sqlitedemo1.sql.AppointmentDao
import kotlinx.android.synthetic.main.activity_appointments.*

class AppointmentsActivity : AppCompatActivity() {
    lateinit var adapter: AppointmentAdapter
    lateinit var aptDao: AppointmentDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        aptDao = AppointmentDao(baseContext)
        adapter = AppointmentAdapter(aptDao.getAllAppointments())
        rv_apts.layoutManager = LinearLayoutManager(this)
        rv_apts.adapter = adapter

    }
}