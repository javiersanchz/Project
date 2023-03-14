package com.example.tfg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tfg.databinding.ActivityRegistrarseBinding

class Registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        val binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}