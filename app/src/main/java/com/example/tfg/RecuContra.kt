package com.example.tfg

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tfg.databinding.ActivityRecuContraBinding
import com.google.firebase.auth.FirebaseAuth

class RecuContra : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var buttonResetPassword: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var mDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recu_contra)

        editTextEmail = findViewById(R.id.editTextEmail)
        buttonResetPassword = findViewById(R.id.buttonResetPassword)

        auth = FirebaseAuth.getInstance()
        mDialog = ProgressDialog(this)

        buttonResetPassword.setOnClickListener {
            val email = editTextEmail.text.toString().trim()

            if (email.isNotEmpty()) {
                mDialog.setMessage("Espere un momento por favor...")
                mDialog.setCanceledOnTouchOutside(false)
                mDialog.show()
                resetPassword(email)
            } else {
                Toast.makeText(
                    this,
                    "Por favor, introduce tu correo electrónico",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }

    private fun resetPassword(email: String) {

            auth.setLanguageCode("es")
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(
                            this,
                            "Se ha enviado un correo electrónico para restablecer la contraseña",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "No se pudo enviar el correo electrónico de restablecimiento de contraseña",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    mDialog.dismiss()
                }


    }
}