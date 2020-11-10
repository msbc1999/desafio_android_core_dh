package me.mateus.digitalhousefoods.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_restaurante.*
import me.mateus.digitalhousefoods.R
import me.mateus.digitalhousefoods.core.Restaurante
import me.mateus.digitalhousefoods.core.RestauranteItem

class RestauranteActivity : AppCompatActivity() {

    private lateinit var restaurante: Restaurante

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurante)
        restaurante = intent.getSerializableExtra("restaurante") as Restaurante

        setSupportActionBar(tbDetalhesRestaurante)

        tbDetalhesRestaurante.title = restaurante.nome
        imgDetalhesRestaurante.setImageResource(restaurante.imagemLogo)

        rvDetalhesRestaurante.adapter = RestauranteItemAdapter(
            restaurante.cardapio
        ).apply {
            restauranteItemClickListener = object :
                RestauranteItemClickListener {
                override fun onRestauranteItemClick(
                    ri: RestauranteItem,
                    rivh: RestauranteItemViewHolder
                ) {
                    Toast.makeText(
                        this@RestauranteActivity,
                        "Clicou no item ${ri.nome}!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        rvDetalhesRestaurante.layoutManager = GridLayoutManager(this, 2)
        rvDetalhesRestaurante.setHasFixedSize(true)
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}


interface RestauranteItemClickListener {
    fun onRestauranteItemClick(
        restauranteItem: RestauranteItem,
        restauranteItemViewHolder: RestauranteItemViewHolder
    )
}


class RestauranteItemAdapter(private val itens: List<RestauranteItem>) :
    RecyclerView.Adapter<RestauranteItemViewHolder>() {

    var restauranteItemClickListener = object :
        RestauranteItemClickListener {
        override fun onRestauranteItemClick(ri: RestauranteItem, rivh: RestauranteItemViewHolder) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteItemViewHolder {
        return RestauranteItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_card_restaurante_item,
                parent,
                false
            ),
            this
        )
    }

    override fun getItemCount() = itens.size

    override fun onBindViewHolder(holder: RestauranteItemViewHolder, position: Int) {
        holder.apply {
            item = itens[position]
            updateViewData()
        }
    }

}


class RestauranteItemViewHolder(
    view: View,
    private val adapter: RestauranteItemAdapter
) : RecyclerView.ViewHolder(view) {

    private var cpImagem: ImageView = view.findViewById(R.id.imgItemImagem)
    private var cpNome: TextView = view.findViewById(R.id.lblItemNome)

    var item: RestauranteItem = RestauranteItem()

    init {
        view.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                adapter.restauranteItemClickListener.onRestauranteItemClick(item, this)
            }
        }
    }

    fun updateViewData() {
        cpImagem.setImageResource(item.imagem)
        cpNome.text = item.nome
    }

}


