package com.example.tfg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tfg.databinding.ActivityLoginyRegistroBinding
import com.example.tfg.databinding.ComienzoBinding

class LoginyRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginy_registro)

        val binding = ActivityLoginyRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}