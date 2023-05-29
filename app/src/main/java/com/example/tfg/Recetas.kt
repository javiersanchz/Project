package com.example.tfg


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Recetas : AppCompatActivity() {

    private lateinit var textoAnimacion: String
    private lateinit var textView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas)

        textView = findViewById(R.id.textoRecetas)
        textoAnimacion = "¿Qué preparamos hoy?"

        animarTexto()

        //si das al boton recetas te lleva a la actividad de recetas de comida
        findViewById<View>(R.id.btnComidas).setOnClickListener {
            val intent = Intent(this, Recetascomida::class.java)
            startActivity(intent)
        }

        //si das al boton recetas te lleva a la actividad de recetas de batidos
        findViewById<View>(R.id.btnBatidos).setOnClickListener {
            val intent = Intent(this, RecetaBatidos::class.java)
            startActivity(intent)
        }



    }

    private fun animarTexto() {
        val handler = Handler(Looper.getMainLooper())

        val builder = StringBuilder()

        for (i in textoAnimacion.indices) {
            handler.postDelayed({
                builder.append(textoAnimacion[i])
                textView.text = builder.toString()
            }, i * 150L)
        }
    }
}
