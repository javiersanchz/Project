package com.example.tfg

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

class Noticias : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: NoticiasAdapter
    private lateinit var noticias: ArrayList<Noticia>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)

        listView = findViewById(R.id.listView)
        noticias = arrayListOf(
            Noticia("Información", "Deporte y veganismo ¿desventaja deportiva?", "Una alimentación cuidada dentro de la dieta vegana, puede llevar a un deportista a convertirse en un atleta con una dieta calórica y nutritiva de igual calidad o mejor que una persona que no lo es...", "https://www.vegaffinity.com/comunidad/articulo/deporte-y-veganismo-desventaja-deportiva--a87",R.drawable.noticiadeporteyvega),
            Noticia("Gastronomía", "7 recetas con TOFU fáciles (y propiedades de este alimento)", "Hoy vamos a ver cómo preparar recetas con tofu tanto saladas como dulces, además de ver las propiedades y beneficios de este ingrediente cada vez más habitual en la cocina...", "https://www.pequerecetas.com/receta/recetas-con-tofu-propiedades/",R.drawable.noticasrecetastofu),
            Noticia("Información", "¿Las dietas veganas son viables para las mascotas?", "Las tendencias globales hacia una dieta libre de carne se han vuelto un tópico en la población, lo que ha provocado que algunos dueños de perros y gatos consideren implementar estas costumbres...", "https://www.infobae.com/america/perrosygatos/2023/01/17/las-dietas-veganas-son-viables-para-las-mascotas/",R.drawable.noticiasanimales),
            Noticia("Salud", "Las dietas basadas en plantas pueden reducir el riesgo de cáncer de próstata, según la ciencia", "Un estudio publicado en la revista Nature indicó que alimentación Plant Based tiene potencial para mejorar los resultados de estos tumores, además del cáncer de colon. Cuáles son las claves para iniciar un cambio...", "https://www.infobae.com/america/ciencia-america/2023/01/09/las-dietas-basadas-en-plantas-pueden-reducir-el-riesgo-de-cancer-de-prostata-segun-la-ciencia/",R.drawable.noticiabeneficios),
            Noticia("Gastronomía", "Tuntún: la primera alternativa vegana al pescado", "Sol Natural lanza Tuntún, la primera alternativa vegana y ecológica al pescado en conserva. Dos opciones 100% vegetales, con una textura y sabor muy similares al atún y la caballa...", "https://www.buenoyvegano.com/2023/05/16/tuntun-la-primera-alternativa-vegana-y-ecologica-con-textura-y-sabor-a-pescado/",R.drawable.noticiapescadovegano),
        )

        adapter = NoticiasAdapter(this, noticias)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val noticia = noticias[position]
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(noticia.enlace))
            startActivity(intent)
        }
    }

    private inner class NoticiasAdapter(
        private val context: Context,
        private val noticias: List<Noticia>
    ) : BaseAdapter() {

        override fun getCount(): Int {
            return noticias.size
        }

        override fun getItem(position: Int): Any {
            return noticias[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View
            val viewHolder: ViewHolder

            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(R.layout.list_item_noticia, null)
                viewHolder = ViewHolder()
                viewHolder.tipoTextView = view.findViewById(R.id.tipoTextView)
                viewHolder.tituloTextView = view.findViewById(R.id.tituloTextView)
                viewHolder.descripcionTextView = view.findViewById(R.id.descripcionTextView)
                viewHolder.leerTextView = view.findViewById(R.id.leerTextView)
                viewHolder.fotoImageView = view.findViewById(R.id.fotoImageView)

                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = convertView.tag as ViewHolder
            }

            val noticia = noticias[position]

            viewHolder.tipoTextView.text = noticia.tipo
            viewHolder.tituloTextView.text = noticia.titulo
            viewHolder.descripcionTextView.text = noticia.descripcion
            viewHolder.fotoImageView.setImageResource(noticia.imagen) // Establecer la imagen en el ImageView

            viewHolder.leerTextView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(noticia.enlace))
                startActivity(intent)
            }

            return view
        }

    }

    private inner class ViewHolder {
        lateinit var tipoTextView: TextView
        lateinit var tituloTextView: TextView
        lateinit var descripcionTextView: TextView
        lateinit var leerTextView: TextView
        lateinit var fotoImageView: ImageView
    }

    private data class Noticia(
        val tipo: String,
        val titulo: String,
        val descripcion: String,
        val enlace: String,
        val imagen: Int
    )
}
