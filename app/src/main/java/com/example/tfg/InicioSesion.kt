package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.tfg.databinding.ActivityInicioSesionBinding
import com.google.firebase.auth.FirebaseAuth

class InicioSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        val binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()

    }

    private fun setup() {
        title = "Inicio sesion"

        val binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonInicioSesion.setOnClickListener {
            if (binding.textoNombre.text.isNotEmpty() && binding.textoContra.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.textoNombre.text.toString(), binding.textoContra.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", tipo.JUGADOR)
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, tipo: tipo) {
        val homeIntent: Intent = Intent(this@login, usersesion::class.java).apply {
            putExtra("email", email)
            putExtra("tipo", tipo.name)
        }
        startActivity(homeIntent)
    }
}