package com.example.tfg

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tfg.databinding.ActivityLoginyRegistroBinding
import com.example.tfg.databinding.ComienzoBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginyRegistro : AppCompatActivity() {
    private val GOOGLE_SIGN_IN = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginy_registro)

        val binding = ActivityLoginyRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonLogin.setOnClickListener() {
            val intent = Intent(this@LoginyRegistro, InicioSesion::class.java)
            startActivity(intent)
        }

        binding.botonRegistrarse.setOnClickListener() {
            val intent = Intent(this@LoginyRegistro, Registrarse::class.java)
            startActivity(intent)
        }


        binding.botonGoogle.setOnClickListener{
            val googleConfig =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
            val googleClient = GoogleSignIn.getClient(this, googleConfig)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val cuenta = task.getResult(ApiException::class.java)
                if (cuenta != null){
                    val credential:AuthCredential = GoogleAuthProvider.getCredential(cuenta.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{
                        if (it.isSuccessful){
                            showHome(cuenta.email ?: "")
                        }else{
                            showAlert()
                        }
                    }
                }
            }catch (e: ApiException){
                showAlert()
            }

        }


    }

    private fun showHome(email: String) {
        val homeIntent: Intent = Intent(this@LoginyRegistro, pantallaPrincipal::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }

    private fun showAlert() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: androidx.appcompat.app.AlertDialog = builder.create()
        dialog.show()
    }


}