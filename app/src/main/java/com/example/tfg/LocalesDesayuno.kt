package com.example.tfg

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class LocalesDesayuno : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: RestauranteAdapter
    private lateinit var restaurantes: ArrayList<Locales>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locales_desayuno)

            listView = findViewById(R.id.listView2)

            restaurantes = arrayListOf(
                Locales("VegAmazing Doughnuts", "Tienda de donuts vegana, con variedad de dulces", R.drawable.vegamazing, "VegAmazing Doughnuts"),
                Locales("Freedom Cakes Café", "Cafetería retro con dulces clásicos veganos", R.drawable.freedomcakes, "Freedom Cakes Café"),
                Locales("Dolce&Vegana enjoy ethically", "Pastelería vegana con variedad de dulces", R.drawable.dolcevegana, "Dolce&Vegana enjoy ethically"),
                Locales("Delish Vegan Doughnuts", "Panadería con variedad de productos creativos veganos", R.drawable.delishvegan, "Delish Vegan Doughnuts"),
                Locales("Bite Me Café", "Local especializado en donuts veganos con variedad de dulces", R.drawable.bitemecafe, "Bite Me Café")
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
            private val restaurantes: List<Locales>
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

        private data class Locales(
            val nombre: String,
            val descripcion: String,
            val imagen: Int,
            val mapa: String
        )
    }

