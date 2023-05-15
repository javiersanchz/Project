package com.example.tfg

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class Suplementos : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var suplementosAdapter: SuplementosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suplementos)

        viewPager = findViewById(R.id.viewPager)

        val suplementos = listOf(
            Suplemento("Suplemento 1", "Descripción del suplemento 1", R.drawable.logoapp, "https://www.example.com/suplemento1"),
            Suplemento("Suplemento 2", "Descripción del suplemento 2", R.drawable.logoapp, "https://www.example.com/suplemento2"),
            Suplemento("Suplemento 3", "Descripción del suplemento 3", R.drawable.logoapp, "https://www.example.com/suplemento3"),
            Suplemento("Suplemento 3", "Descripción del suplemento 3", R.drawable.logoapp, "https://www.example.com/suplemento3")

            // Agrega más suplementos si es necesario
        )

        suplementosAdapter = SuplementosAdapter(this, suplementos)
        viewPager.adapter = suplementosAdapter
    }

    inner class SuplementosAdapter(private val context: Context, private val suplementos: List<Suplemento>) :
        RecyclerView.Adapter<SuplementosAdapter.SuplementoViewHolder>() {

        inner class SuplementoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            init {
                itemView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val url = suplementos[position].url
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                }
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuplementoViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_suplemento, parent, false)
            return SuplementoViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: SuplementoViewHolder, position: Int) {
            val currentItem = suplementos[position]
            holder.itemView.findViewById<TextView>(R.id.textNombreSuplemento).text = currentItem.nombre
            holder.itemView.findViewById<TextView>(R.id.textDescripcionSuplemento).text = currentItem.descripcion
            holder.itemView.findViewById<ImageView>(R.id.imageSuplemento).setImageResource(currentItem.imagen)
        }

        override fun getItemCount(): Int {
            return suplementos.size
        }
    }

    data class Suplemento(val nombre: String, val descripcion: String, val imagen: Int, val url: String)
}