package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.example.tfg.databinding.ComienzoBinding


class Comienzo : AppCompatActivity() {

    private lateinit var textoAnimacion: String
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comienzo)

        val binding = ComienzoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        textView = findViewById(R.id.textoComienzo)
        textoAnimacion = "¡Cuida el planeta, cuida tu salud!"

        animarTexto()

        binding.botonComenzar.setOnClickListener{
            val intent = Intent(this@Comienzo, LoginyRegistro::class.java)
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