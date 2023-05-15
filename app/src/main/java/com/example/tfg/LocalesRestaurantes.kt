package com.example.tfg

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LocalesRestaurantes : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: RestauranteAdapter
    private lateinit var restaurantes: ArrayList<Restaurante>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locales_restaurantes)

        listView = findViewById(R.id.listView)

        restaurantes = arrayListOf(
            Restaurante("Choose Restaurante Vegano", "Restaurante que sirve versiones veganas de platos típicos italianos, incluidas pastas, pizzas y risottos", R.drawable.chooserestaurante, "Choose Restaurante Vegano"),
            Restaurante("Punto Vegano", "Restaurante de comida vegetariana estricta con variedad de platos", R.drawable.puntovegano, "Punto Vegano"),
            Restaurante("Distrito Vegano Invernadero", "Restaurante de comida vegetariana estricta con variedad de platos", R.drawable.distritovegano, "Distrito Vegano Invernadero"),
            Restaurante("Mudrá Plant Based Food - Restaurante Vegano", "Restaurante refinado y moderno con un menú de platos veganos creativos.", R.drawable.mudraplant, "Mudrá Plant Based Food - Restaurante Vegano "),
            Restaurante("VEGA", "Restaurante chic de cocina vegana con platos veganos, postres y bebidas naturales", R.drawable.vega, "VEGA")
        )

        adapter = RestauranteAdapter(this, restaurantes)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val restaurante = restaurantes[position]
            val location = Uri.encode(restaurante.mapa)
            val uri = Uri.parse("geo:0,0?q=$location")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }

    private inner class RestauranteAdapter(
        private val context: Context,
        private val restaurantes: List<Restaurante>
    ) : BaseAdapter() {

        override fun getCount(): Int {
            return restaurantes.size
        }

        override fun getItem(position: Int): Any {
            return restaurantes[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View
            val viewHolder: ViewHolder

            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(R.layout.list_item_restaurante, null)
                viewHolder = ViewHolder()
                viewHolder.nombreTextView = view.findViewById(R.id.nombreTextView)
                viewHolder.descripcionTextView = view.findViewById(R.id.descripcionTextView)
                viewHolder.buscarTextView = view.findViewById(R.id.buscarTextView)
                viewHolder.fotoImageView = view.findViewById(R.id.fotoImageView)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = convertView.tag as ViewHolder
            }

            val restaurante = restaurantes[position]

            viewHolder.nombreTextView.text = restaurante.nombre
            viewHolder.descripcionTextView.text = restaurante.descripcion
            viewHolder.buscarTextView.setOnClickListener {
                val location = Uri.encode(restaurante.mapa)
                val uri = Uri.parse("geo:0,0?q=$location")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)
            }

            viewHolder.fotoImageView.setImageResource(restaurante.imagen)

            return view
        }


    }

    private inner class ViewHolder {
        lateinit var nombreTextView: TextView
        lateinit var descripcionTextView: TextView
        lateinit var buscarTextView: TextView
        lateinit var fotoImageView: ImageView
    }

    private data class Restaurante(
        val nombre: String,
        val descripcion: String,
        val imagen: Int,
        val mapa: String
    )
}

