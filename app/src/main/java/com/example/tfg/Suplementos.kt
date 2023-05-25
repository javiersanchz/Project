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
            Suplemento("Vitamina B12", "La B12 es una vitamina hidrosoluble esencial que ayuda a mantener sanas las neuronas para el correcto funcionamiento del cerebro y del sistema nervioso, así como para la formación de los glóbulos rojos de la sangre y de diversas proteínas fundamentales para el organismo\n" +
                    "\n" +
                    "Esta vitamina podemos encontrarla principalmente en alimentos de origen animal como el pescado, la carne, las aves, los huevos, la leche y otros productos lácteos.\n" +
                    "\n" +
                    "La cantidad de B12 que se requiere por día depende de la edad, pero el promedio diario para una persona adulta es de 2,4 microgramos", R.drawable.vitaminab12, "https://www.myprotein.es/nutricion-deportiva/vitamina-b12-vegana/12071144.html"),
            Suplemento("Calcio", "El calcio es un mineral que el cuerpo necesita para formar y mantener huesos fuertes y llevar a cabo muchas funciones importantes. El calcio es el mineral más abundante en el organismo.\n" +
                    "\n" +
                    "La leche, el yogur y el queso son las principales fuentes de calcio, algunos productos enlatados, como sardinas y salmón con espinas, contienen calcio.\n" +
                    "\n" +
                    "La dosis diaria recomendada en un adulto está en torno a los 1000mg diarios\n", R.drawable.calcio1, "https://www.myprotein.es/nutricion-deportiva/calcio-y-magnesio-comprimidos/11311254.html"),
            Suplemento("Omega 3", "Los omega 3 ayuda en la prevención de enfermedades cardiovasculares, diabetes y enfermedades del sistema inmunitario.\n" +
                    "\n" +
                    "Esta vitamina podemos encontrarla principalmente pescado y mariscos (en especial, pescados grasos de agua fría, como salmón, caballa, atún, arenques, y sardinas), nueces, semillas, aceites de plantas entre otros.\n" +
                    "\n" +
                    "La cantidad diaria recomendada para adultos mayores de 19 años es de 1,2 g para las mujeres y de 1,6 g para los hombres.", R.drawable.omega3, "https://aavalabs.com/products/vegan-omega-3?utm_source=google&utm_medium=cpc&gclid=CjwKCAjw67ajBhAVEiwA2g_jEKY5Sv49Af9QXXIdKcu8n5xrr6EMVGUANjEohE-p0_GzwnPU3c9XNhoCr08QAvD_BwE"),
            Suplemento("Hierro", "El hierro se encarga del transporte y almacenamiento de oxigeno en los tejidos, actúa sobre el sistema nervioso y metabolismo de la energía.\n" +
                    "\n" +
                    "Lo podemos encontrar en mayor abundancia en carnes magras, mariscos y aves, otros alimentos como nueces o algunas frutas secas como las pasas.\n" +
                    "\n" +
                    "Los veganos tienen una recomendación de hierro diaria de 1,8 veces más que los no vegetarianos, la dosis recomendada diaria esta en torno a los 12mg.", R.drawable.hierro, "https://aavalabs.com/products/iron-with-vitamin-c?_pos=1&_sid=02bb987c6&_ss=r")
            // Agrega más suplementos si es necesario
        )

        if (suplementos.isNotEmpty()) {
            suplementosAdapter = SuplementosAdapter(this, suplementos)
            viewPager.adapter = suplementosAdapter
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    suplementosAdapter.updateArrowVisibility(position)
                }
            })
        }
    }

    inner class SuplementosAdapter(private val context: Context, private val suplementos: List<Suplemento>) :
        RecyclerView.Adapter<SuplementosAdapter.SuplementoViewHolder>() {

        private val arrowVisibility = mutableListOf<Int>()

        init {
            // Inicializar el tamaño de arrowVisibility con el mismo tamaño que la lista de suplementos
            for (i in suplementos.indices) {
                arrowVisibility.add(View.VISIBLE) // Cambiado a View.VISIBLE para mostrar ambas flechas
            }
        }

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
            holder.itemView.findViewById<TextView>(R.id.textDescripcionSuplemento).text =
                currentItem.descripcion
            holder.itemView.findViewById<ImageView>(R.id.imageSuplemento).setImageResource(currentItem.imagen)

            val imageArrowLeft: ImageView = holder.itemView.findViewById(R.id.imageArrowLeft)
            imageArrowLeft.visibility = arrowVisibility[position]

            val imageArrowRight: ImageView = holder.itemView.findViewById(R.id.imageArrowRight)
            imageArrowRight.visibility = arrowVisibility[position]

            // Manejo de clic en la flecha de navegación izquierda
            imageArrowLeft.setOnClickListener {
                if (position > 0) {
                    viewPager.currentItem = position - 1
                }
            }

            // Manejo de clic en la flecha de navegación derecha
            imageArrowRight.setOnClickListener {
                if (position < suplementos.size - 1) {
                    viewPager.currentItem = position + 1
                }
            }
        }


        override fun getItemCount(): Int {
            return suplementos.size
        }

        fun updateArrowVisibility(currentPosition: Int) {
            arrowVisibility.clear()
            for (i in 0 until suplementos.size) {
                arrowVisibility.add(View.VISIBLE) // Cambiado a View.VISIBLE para mostrar ambas flechas
            }
            notifyDataSetChanged()
        }

    }

    data class Suplemento(val nombre: String, val descripcion: String, val imagen: Int, val url: String)
}
