package com.aapolis.sqlitedemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aapolis.sqlitedemo1.adapters.DoctorAdapter
import com.aapolis.sqlitedemo1.sql.DoctorDao
import kotlinx.android.synthetic.main.activity_doctors.*

class DoctorsActivity : AppCompatActivity() {
    lateinit var adapter: DoctorAdapter
    lateinit var doctorDao: DoctorDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctors)

        doctorDao = DoctorDao(baseContext)
        adapter = DoctorAdapter(doctorDao.getDoctors())

        rv_doctors.layoutManager = LinearLayoutManager(baseContext)
        rv_doctors.adapter = adapter

        adapter.setOnDoctorSelectedListener { doctor, position ->
            val data = Intent()
            data.putExtra("doctor", doctor)
            setResult(RESULT_OK, data)
            finish()
        }

    }
}