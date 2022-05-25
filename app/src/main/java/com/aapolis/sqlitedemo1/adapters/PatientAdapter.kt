package com.aapolis.sqlitedemo1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.sqlitedemo1.R
import com.aapolis.sqlitedemo1.data.Patient
import com.aapolis.sqlitedemo1.viewholders.PatientViewHolder

class PatientAdapter(val patients: List<Patient>) : RecyclerView.Adapter<PatientViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.view_holder_patient, parent, false)
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.setData(patients[position])

        holder.ivMore.setOnClickListener {
            holder.popupMenu.show()
        }

        holder.popupMenu.setOnMenuItemClickListener {
            when (it.title) {
                "Edit" -> {
                    if (this::editClickListener.isInitialized) {
                        editClickListener(patients[position], position)
                    }
                }
                "Delete" -> {
                    if (this::deleteClickListener.isInitialized) {
                        deleteClickListener(patients[position], position)
                    }
                }
            }
            true
        }

        /*if (this::deleteClickListener.isInitialized) {
            holder.btnDelete.setOnClickListener {
                deleteClickListener(patients[position], position)
            }
        }


        if (this::editClickListener.isInitialized) {
            holder.btnEdit.setOnClickListener {
                editClickListener(patients[position], position)
            }
        }*/

        if (this::patientSelectedListener.isInitialized) {
            holder.itemView.setOnClickListener {
                patientSelectedListener(patients[position], position)
            }
        }
    }

    override fun getItemCount() = patients.size

    lateinit var deleteClickListener: (Patient, Int) -> Unit

    fun setOnDeleteClickListener(listener: (Patient, Int) -> Unit) {
        this.deleteClickListener = listener
    }

    lateinit var editClickListener: (Patient, Int) -> Unit

    fun setOnEditClickListener(listener: (Patient, Int) -> Unit) {
        this.editClickListener = listener
    }

    lateinit var patientSelectedListener: (Patient, Int) -> Unit

    fun setOnPatientSelectedListener(listener: (Patient, Int) -> Unit) {
        this.patientSelectedListener = listener
    }
}