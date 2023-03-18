package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.tfg.databinding.ActivityRegistrarseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Registrarse : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        val binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()


    }

    private fun setup() {
        title = "Autenticación"
        val binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonRegistrarse.setOnClickListener {
            if (binding.textoEmailRegistro.text.isNotEmpty() && binding.textoContraRegistro.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.textoEmailRegistro.text.toString(),
                    binding.textoContraRegistro.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "")
                    } else {
                        showAlert()
                    }
                }
            }

        }


    }
    private fun showAlert() {
        val binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
       /* if (binding.textoContraRegistro.text != binding.textoRepetirContraRegistro.text) {
            builder.setMessage("Las contraseñas no coinciden")
        } else {
            builder.setMessage("Se ha producido un error autenticando al usuario")
        }*/
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



    private fun showHome(email: String) {
        val binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homeIntent: Intent = Intent(this@Registrarse, InicioSesion::class.java).apply {
            var nombre = binding.textoNombreRegistro.text.toString()
            var apellido = binding.textoPrimerApellidoRegistro.text.toString()
            var email = binding.textoEmailRegistro.text.toString()
            val usuario = Usuario(nombre, apellido, email)
            usuario.setNombre(nombre)
            usuario.setApellido(apellido)
            usuario.setCorreo(email)
            db.collection("usuarios").document(usuario.getCorreo()).set(hashMapOf(
                "nombre" to usuario.getNombre(),
                "apellido" to usuario.getApellido(),
                "correo" to usuario.getCorreo()
            ))
        }
        startActivity(homeIntent)
    }
}