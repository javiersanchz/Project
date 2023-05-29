package com.example.tfg

import android.annotation.SuppressLint
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

class RecetaBatidos : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var recetasAdapter: RecetasAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetascomida)

        viewPager = findViewById(R.id.viewPager)

        val recetas = listOf(
            Receta("Batido de maca y chocolate: 1per","INGREDIENTES:\n" +
                    "1 cucharada de maca en polvo TerraSana\n" +
                    "1 cucharada de cacao en polvo TerraSana\n" +
                    "1 cucharadita de pasta de almendra blanca\n" +
                    "200 ml de bebida de arroz\n" +
                    "1 cucharada de jarabe de arce grado C TerraSana","PREPARACIÓN:\n" +
                    "1. Poner todos los ingredientes en la batidora ¡y listo! Muy fácil y con un resultado exquisito. Puedes decorarlo con unos trocitos de plátano.\n" +
                    "\n\n" +
                    "TRUCO:\n" +
                    "- Añadir medio plátano para hacer el batido más cremoso y dulce.",R.drawable.recetabatidomaca),
            Receta("Crema dulce de cúrcuma, caramelo y delicias de chocolate: 1per","INGREDIENTES:\n" +
                    "- Un plátano congelado\n" +
                    "- Medio Mango congelado\n" +
                    "- 200ml Leche de coco de TerraSana\n" +
                    "- 1 cucharada de Azúcar de flor de coco de TerraSana\n" +
                    "- 1 cucharada de Cúrcuma latte de TerraSana","PREPARACIÓN:\n" +
                    "1. Cortar el plátano y el mango en trozos la noche anterior y poner en el congelador hasta el día siguiente.:\n\n" +
                    "2. Antes de empezar, poner el vaso en el congelador para que se enfríe bien durante la elaboración.:\n\n" +
                    "3. Verter aproximadamente 125 ml de la parte líquida de la leche de coco en una cacerola y mezclar con azúcar de flor de coco. Dejar que se espese a fuego medio durante unos 15 minutos hasta que se convierta en una salsa de caramelo espesa.:\n\n" +
                    "4. Moler la bolsita de gojiberry mix finamente en la batidora y reservar.:\n\n" +
                    "5. Poner el plátano, el mango, el latte de cúrcuma y el resto de la leche de coco en la licuadora y moler hasta obtener una crema fina.:\n\n" +
                    "6. Sacar el vaso del congelador. Sumergir la parte superior en la salsa de caramelo y luego en la mezcla de gojiberry mix triturada. Dejar que un poco de salsa de caramelo extra se deslice por los bordes.:\n\n" +
                    "7. Servir el batido en el vaso.:\n\n" +
                    "8. Decorar el batido con las delicias de chocolate, las tiras de gofre de avellana y el resto de la mezcla de gojiberry mix.",R.drawable.logoapp)
        )

        if (recetas.isNotEmpty()) {
            recetasAdapter = RecetasAdapter(this, recetas)
            viewPager.adapter = recetasAdapter
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    recetasAdapter.updateArrowVisibility(position)
                }
            })
        }
    }

    inner class RecetasAdapter(private val context: Context, private val recetas: List<Receta>) :
        RecyclerView.Adapter<RecetasAdapter.RecetaViewHolder>() {

        private val arrowVisibility = mutableListOf<Int>()

        init {
            // Inicializar el tamaño de arrowVisibility con el mismo tamaño que la lista de recetas
            for (i in recetas.indices) {
                arrowVisibility.add(View.VISIBLE) // Cambiado a View.VISIBLE para mostrar ambas flechas
            }
        }

        inner class RecetaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            init {
                itemView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        // Realizar acciones al hacer clic en una receta (abrir enlace, etc.)
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_receta, parent, false)
            return RecetaViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
            val currentItem = recetas[position]
            holder.itemView.findViewById<TextView>(R.id.textNombreReceta).text = currentItem.nombre
            holder.itemView.findViewById<TextView>(R.id.textIngredientes).text = currentItem.ingredientes
            holder.itemView.findViewById<TextView>(R.id.textPreparacion).text = currentItem.preparacion
            holder.itemView.findViewById<ImageView>(R.id.imageReceta).setImageResource(currentItem.imagen)

            val imageArrowLeft: ImageView = holder.itemView.findViewById(R.id.imageArrowLeft2) // Corregido el ID aquí
            imageArrowLeft.visibility = arrowVisibility[position]

            val imageArrowRight: ImageView = holder.itemView.findViewById(R.id.imageArrowRight2) // Corregido el ID aquí
            imageArrowRight.visibility = arrowVisibility[position]

            // Manejo de clic en la flecha de navegación izquierda
            imageArrowLeft.setOnClickListener {
                viewPager.setCurrentItem(position - 1, true)
            }

            // Manejo de clic en la flecha de navegación derecha
            imageArrowRight.setOnClickListener {
                viewPager.setCurrentItem(position + 1, true)
            }
        }


        override fun getItemCount(): Int {
            return recetas.size
        }

        fun updateArrowVisibility(currentPosition: Int) {
            for (i in arrowVisibility.indices) {
                if (i == currentPosition) {
                    arrowVisibility[i] = View.VISIBLE
                } else {
                    arrowVisibility[i] = View.INVISIBLE
                }
            }
            notifyDataSetChanged()
        }
    }

    data class Receta(val nombre: String, val ingredientes: String, val preparacion: String, val imagen: Int)
}
