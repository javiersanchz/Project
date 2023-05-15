package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.example.tfg.databinding.ActivityPantallaPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class pantallaPrincipal : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var textoAnimacion: String
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)



        //binding
        val binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val userEmail = auth.currentUser?.email
        val documentReference = userEmail?.let { firestore.collection("usuarios").document(it) }
        if (documentReference != null) {
            documentReference.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val nombre = documentSnapshot.getString("nombre")
                    val saludo = obtenerSaludo()
                    val mensaje = "$saludo $nombre"
                    textView = findViewById<TextView>(R.id.textSaludo)
                    textoAnimacion = mensaje
                    // Aquí puedes mostrar el mensaje en tu pantalla principal

                    animarTexto()
                }
            }.addOnFailureListener { exception ->
                // Maneja el error si la lectura de datos falla
            }
        }

        //que el boton de restaurantes te lleve a la pantalla de restaurantes
        binding.boton4.setOnClickListener {
            val intent = Intent(this, BuscarLocales::class.java)
            startActivity(intent)
        }

        binding.boton3.setOnClickListener {
            val intent = Intent(this, Suplementos::class.java)
            startActivity(intent)
        }
    }

    private fun obtenerSaludo(): String {
        val calendar = Calendar.getInstance()
        val hora = calendar.get(Calendar.HOUR_OF_DAY)
        return when (hora) {
            in 6 until 12 -> "Buenos días"
            in 12 until 20 -> "Buenas tardes"
            else -> "Buenas noches"
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