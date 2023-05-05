package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tfg.databinding.ActivityInicioSesionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class InicioSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setup()
    }

    private fun setup() {
        title = "Inicio de sesión"

        val binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonInicioSesion.setOnClickListener {
            val email = binding.textoNombre.text.toString().trim()
            val password = binding.textoContra.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            showHome(task.result?.user?.email ?: "")
                        } else {
                            val exception = task.exception
                            if (exception is FirebaseAuthInvalidUserException) {
                                if (exception.errorCode == "ERROR_USER_NOT_FOUND") {
                                    Toast.makeText(this, "El correo electrónico no está registrado", Toast.LENGTH_SHORT).show()
                                } else if (exception.errorCode == "ERROR_WRONG_PASSWORD") {
                                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                showAlert()
                            }
                        }
                    }
            }
        }

        binding.botonRecuperarContrase.setOnClickListener {
            // Enviar a la pantalla de recuperar contraseña
            val intent = Intent(this, RecuContra::class.java)
            startActivity(intent)
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

    private fun showHome(email: String) {
        val homeIntent: Intent = Intent(this@InicioSesion, pantallaPrincipal::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }
}