package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tfg.databinding.ActivityRegistrarseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Registrarse : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarseBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {
        title = "Autenticación"

        binding.botonRegistrarse.setOnClickListener {
            val nombre = binding.textoNombreRegistro.text.toString().trim()
            val email = binding.textoEmailRegistro.text.toString().trim()
            val password = binding.textoContraRegistro.text.toString().trim()
            val confirmPassword = binding.textoRepetirContraRegistro.text.toString().trim()
            val apellido = binding.textoPrimerApellidoRegistro.text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || apellido.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            } else {
                if (password == confirmPassword) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val userEmail = task.result?.user?.email ?: ""
                                showHome(userEmail)
                            } else {
                                showAlert("Error", "Se ha producido un error al autenticar al usuario")
                            }
                        }
                } else {
                    showAlert("Error", "Las contraseñas no coinciden")
                }
            }
        }
    }

    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String) {
        val homeIntent = Intent(this@Registrarse, InicioSesion::class.java).apply {
            var nombre = binding.textoNombreRegistro.text.toString()
            var apellido = binding.textoPrimerApellidoRegistro.text.toString()
            var email = binding.textoEmailRegistro.text.toString()
            val usuario = Usuario(nombre, apellido, email)
            usuario.setNombre(nombre)
            usuario.setApellido(apellido)
            usuario.setCorreo(email)
            db.collection("usuarios").document(usuario.getCorreo()).set(
                hashMapOf(
                    "nombre" to usuario.getNombre(),
                    "apellido" to usuario.getApellido(),
                    "correo" to usuario.getCorreo()
                )
            )
        }
        startActivity(homeIntent)
        finish()
    }
}