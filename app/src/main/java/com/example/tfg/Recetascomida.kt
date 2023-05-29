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

class Recetascomida : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var recetasAdapter: RecetasAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetascomida)

        viewPager = findViewById(R.id.viewPager)

        val recetas = listOf(
            Receta("Tagliatelle con queso de calabaza y espinacas: 3pers", "INGREDIENTES:\n" +
                    "- 1 paquete de tagliatelle de espelta con espinacas Sol Natural\n" +
                    "- 3 tazas de calabaza en dados\n" +
                    "- 1 cucharada de aceite de oliva virgen extra\n" +
                    "- 1 cucharadita de sal\n" +
                    "- 1 taza y ½ de bebida vegetal de avena sin gluten\n" +
                    "- 4 cucharadas de levadura nutricional\n" +
                    "- 2 tazas de espinacas frescas\n" +
                    "- 2 cucharadas de queso rallado (opcional)\n", "PREPARACIÓN:\n" +
                    "1. Corta la calabaza en dados, añade a una bandeja apta para horno con un poco de aceite de oliva y una pizca de sal. Hornea a 180ºC durante 15-20 minutos o hasta que quede tierna (el tiempo dependerá del tamaño de los dados). Una vez tierna deja enfriar y retira la piel.\n\n" +
                    "2. Mientras la calabaza se hornea, prepara la pasta. Añade los tagliatelle a una olla con agua hirviendo con sal y cocínalos a tu gusto, 6 minutos al dente o 8 minutos si los prefieres más tiernos. Escurre y reserva.\n\n" +
                    "3. Añade la calabaza asada a una batidora junto con la bebida vegetal de avena, la levadura nutricional y una pizca de sal. Tritura hasta obtener una consistencia super cremosa. Ajusta la densidad de la salsa añadiendo más líquido si lo ves necesario.\n\n" +
                    "4. Pasa la salsa a una sartén grande y calienta a fuego medio. Añade las espinacas para que se cocinen unos minutos en la salsa caliente. Incorpora los tagliatelle y mezcla bien. Añade un poco de queso por encima en el momento de servir.", R.drawable.recetatagliatelle),
            Receta("Risotto de setas vegano: 3pers", "INGREDIENTES:\n" +
                    "- 160 gr de arroz carnaroli o arborio.\n" +
                    "- 700 ml de caldo de verduras.\n" +
                    "- 2 cucharadas de aceite de oliva virgen extra.\n" +
                    "- 200 gr de setas.\n" +
                    "- 2 chalotas.\n" +
                    "- 2 dientes de ajo.\n" +
                    "- 50 ml de vino blanco.\n" +
                    "- 80 gr de crema de arroz Cuisine\n", "PREPARACIÓN:\n" +"1. Picar los ajos y la chalota en trocitos pequeños. Calentar el aceite de oliva en una sartén y rehogarlos hasta que estén tiernos.\n\n" +
                    "2. En ese momento, añadir a la sartén las setas troceadas y cocinar dos o tres minutos hasta que se evapore el agua de cocción.\n\n" +
                    "3. Incorporar el arroz y dejar que se cocine hasta que se vuelva ligeramente nacarado. Entonces agregar el vino blanco. Cuando se evapore el alcohol se añade el caldo.\n\n" +
                    "4. Es muy importante, para que el arroz quede meloso, que el caldo se vaya incorporando poco a poco y se mueva con frecuencia. Así el arroz irá soltando el almidón y quedará un resultado perfecto.\n\n" +
                    "5. El caldo debe estar caliente cuando se añade al arroz para que no corte la cocción.\n\n" +
                    "6. Al cabo de 14-16 minutos, el arroz debe estar tierno por fuera pero aun ligeramente al dente en su interior. En este momento llega la hora de mantecar el risotto, en lugar de hacerlo con mantequilla, lo haremos con crema de arroz. Nos proporciona un aporte extra de cremosidad.\n\n" +
                    "7. El risotto debe tomarse recién hecho para que el grano esté perfecto, en caso contrario el arroz se pasará y quedará pastoso.", R.drawable.recetarissoto),
            Receta("Burritos veganos con salsa de remolacha: 1per", "INGREDIENTES:\n" +
                    "- 1 tortilla de trigo\n" +
                    "- ½ cebolla\n" +
                    "- ½ aguacate\n" +
                    "- 2 puñados de espinacas baby\n" +
                    "- Aceite de Oliva\n\n" +
                    "Para la salsa:" +
                    "\n" +
                    "- ½ vasito de aceite de girasol\n" +
                    "- ¼ vasito de leche de soja sin azúcar\n" +
                    "- Chorrito de limón\n" +
                    "- 1 ajo\n" +
                    "- 1 trozo pequeño de remolacha cocida", "PREPARACIÓN:\n" +"1. Triturar con una batidora los ingredientes de la salsa y reservar en la nevera.\n\n" +
                    "2. Picar y saltear la cebolla hasta dorar, engrasando previamente la sartén con aceite de oliva. Reservar.\n\n" +
                    "3. Colocar en la tortilla ½ aguacate en rodajas, las espinacas baby y la cebolla salteada.\n\n" +
                    "4. Añadir salsa por encima y cerrar.\n\n" +
                    "5. Calentar otro chorrito de aceite de oliva en la sartén y dorar el burrito por ambos lados.\n\n" +
                    "6. Servir caliente.", R.drawable.recetaburrito),
            Receta("Gratinado de berenjena y lentejas: 1per", "INGREDIENTES:\n" +
                    "- ½ Berenjena\n" +
                    "- 100 gr. Lentejas cocidas\n" +
                    "- ½ Cebolla\n" +
                    "- ½ taza de salsa de tomate\n" +
                    "- Albahaca\n" +
                    "- Aceite de Oliva","PREPARACIÓN:\n" +"1. Picar y saltear en la sartén la cebolla hasta que adquiera un tono dorado.\n\n" +
                    "2. Añadir las lentejas cocidas, la salsa de tomate y una pizca de albahaca.\n\n" +
                    "3. Mezclar y saltear por 2 minutos más y reservar.\n\n" +
                    "4. Engrasar la base del molde con aceite de oliva y reservar.\n\n" +
                    "5. Laminar finamente la berenjena, preferentemente con una mandolina, y colocar láminas hasta cubrir toda la base del molde para horno.\n\n" +
                    "6. Volcar por encima las lentejas y cubrirlas con más láminas de berenjena.\n\n" +
                    "7. Engrasar las berenjenas superiores con un chorrito de aceite de oliva y calentar en el horno, encendido por arriba y por abajo, a 180 grados.\n\n" +
                    "8. Cuando la berenjena se haya tostado ligeramente, retiramos del horno y servimos caliente.", R.drawable.recetagratinadoberenjena),
            // Agrega más recetas si es necesario

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
