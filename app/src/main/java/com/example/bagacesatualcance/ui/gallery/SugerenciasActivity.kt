package com.example.bagacesatualcance.ui.gallery

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.bagacesatualcance.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_noticias.*
import kotlinx.android.synthetic.main.activity_sugerencias.*
import java.util.*

class SugerenciasActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugerencias)
        title = "Crea tu nueva Sugerencia"


        btnNuevaSugerencia.setOnClickListener{
            db.collection("Sugerencia").document(text_Suge.text.toString()).set(hashMapOf(
                "descripcion" to text_Suge.text.toString()))
            Toast.makeText(this,"Sugerencia publicada con éxito", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

}